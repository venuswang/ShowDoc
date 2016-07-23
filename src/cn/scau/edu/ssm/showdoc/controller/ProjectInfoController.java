package cn.scau.edu.ssm.showdoc.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.scau.edu.ssm.showdoc.po.ProjectExtendClass;
import cn.scau.edu.ssm.showdoc.po.UserProject;
import cn.scau.edu.ssm.showdoc.service.ProjectInfoService;
import cn.scau.edu.ssm.showdoc.service.UserProjectService;
import cn.scau.edu.ssm.showdoc.validator.ValidGroup1;
import cn.scau.edu.ssm.showdoc.validator.ValidGroup4;

/**
 * 创建project的控制类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/project")
public class ProjectInfoController {
	@Autowired
	private ProjectInfoService projectInfoService;
	@Autowired
	private UserProjectService userProjectService;
	
	//加载用户已经创建的项目
	@RequestMapping(value="/showProject.action")
	public String queryProject(Model model,HttpServletRequest request) throws Exception{
		String username = (String)request.getSession().getAttribute("username");
		if(username == null)
			return "forward:/jsp/login/register.jsp";
		List<ProjectExtendClass> projects = projectInfoService.getProjectByName(username);
		model.addAttribute("projects", projects);
		return "forward:/jsp/project/userproject.jsp";
	}
	
	//创建一个新的项目
	@RequestMapping(value="/addProject")
	public void addProject(HttpServletResponse response,@Validated(value={ValidGroup4.class}) ProjectExtendClass projectExtendClass,BindingResult result) throws Exception{
		String message = "";
		if(result.hasErrors())
		{
			List<ObjectError> errors = result.getAllErrors();
			StringBuffer errorInfos = new StringBuffer();
			for(ObjectError error : errors)
			{
				errorInfos.append(error.getDefaultMessage()).append(',');
			}
			if(errorInfos != null && errorInfos.length() > 0)
				errorInfos.deleteCharAt(errorInfos.length() - 1);
			message = "illegal,"+errorInfos.toString();
		} else {
			boolean isSucceed = projectInfoService.insertProject(projectExtendClass);
			if(isSucceed == true)
				message = "success";
			else
				message = "fail";
		}
		response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        out = response.getWriter();
        out.println(message);	
	}
	
	//添加项目协作者
	@RequestMapping(value="/addProjectAuthor/{id}")
	public void addProjectAuthor(HttpServletRequest request,HttpServletResponse response,@PathVariable("id") Integer pid) throws Exception 
	{
		String vname = request.getParameter("vname");
		String message = "";
		if(pid == null || vname == null || "".equals(vname.trim()))
			message = "illegal";
		UserProject userProject = new UserProject(pid,vname);
		boolean result = userProjectService.insertProjectName(userProject);
		if(result == false)
			message = "fail";
		else
			message = "success";
		response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        out = response.getWriter();
        out.println(message);		
	}
	
	//删除项目协作者
	@RequestMapping(value="/deleteProjectAuthor/{id}")
	public void deleteProjectAuthor(HttpServletRequest request,HttpServletResponse response,@PathVariable("id") Integer pid) throws Exception 
	{
		String vname = request.getParameter("vname");
		String message = "";
		if(pid == null || vname == null || "".equals(vname.trim()))
			message = "illegal";
		UserProject userProject = new UserProject(pid,vname);
		boolean result = userProjectService.deleteProjectName(userProject);
		if(result == false)
			message = "fail";
		else
			message = "success";
		response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        out = response.getWriter();
        out.println(message);		
	}
	
	//Ajax异步请求项目名是否被该用户创建过
	@RequestMapping(value="/checkProject/{projectname}")
	public void checkProject(HttpServletRequest request,HttpServletResponse response,@PathVariable("projectname") String projectname) throws Exception 
	{
		String vname = request.getParameter("vname");
		String message = "";
		if(projectname == null || vname == null ||  "".equals(projectname.trim()) || "".equals(vname.trim()))
			message = "illegal";
		boolean result = projectInfoService.queryProjectByName(projectname, vname);
		if(result == false)
			message = "fail";  //表示该项目名已经被该用户创建过
		else
			message = "success"; //表示该项目可以被该用户创建
		response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        out = response.getWriter();
        out.println(message);		
	}
}
