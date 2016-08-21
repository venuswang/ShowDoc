package cn.scau.edu.ssm.showdoc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.scau.edu.ssm.showdoc.po.SubProjectList;
import cn.scau.edu.ssm.showdoc.po.SubProjectResult;
import cn.scau.edu.ssm.showdoc.po.SubprojectExtendClass;
import cn.scau.edu.ssm.showdoc.service.SubProjectInfoService;
import cn.scau.edu.ssm.showdoc.validator.ValidGroup5;
import cn.scau.edu.ssm.showdoc.validator.ValidGroup6;

@Controller
@RequestMapping(value="/subProject")
public class SubProjectInfoController {
	@Autowired
	SubProjectInfoService subProjectInfoService;
	
	
	//添加项目下的文件夹
	@RequestMapping(value="/addSubProject",method={RequestMethod.POST,RequestMethod.GET},produces = {"application/json;charset=UTF-8"})
	public @ResponseBody SubProjectResult addSubProject(HttpServletRequest request, @Validated(value={ValidGroup5.class}) SubprojectExtendClass subprojectExtendClass, BindingResult result) throws Exception {
		SubProjectResult spr = null;
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
			spr = new SubProjectResult();
			spr.setResult(false);
			spr.setMessage(errorInfos.toString());
			return spr;		
		}
		String username = (String)request.getSession().getAttribute("username");
		if(username == null || username.trim().equals("")) 
		{
			spr = new SubProjectResult();
			spr.setResult(false);
			spr.setMessage("用户没有登录");
			return spr;	
		}
		subprojectExtendClass.setSubauthorname(username);
		spr = subProjectInfoService.insertSubProject(subprojectExtendClass);
		return spr;
	}
	
	//搜索某文件夹ID下的所有文件夹
	@RequestMapping(value="/querySubProject/{projectid}/{parentid}",method={RequestMethod.POST,RequestMethod.GET},produces = {"application/json;charset=UTF-8"})
	public @ResponseBody List<SubprojectExtendClass> querySubProject(HttpServletRequest request, @PathVariable("projectid") Integer projectid, @PathVariable("parentid") Integer parentid) throws Exception {
		String username = (String)request.getSession().getAttribute("username");
		List<SubprojectExtendClass> secs = null;
		if(username == null || username.trim().equals("") || projectid == null || parentid == null) 
		{
			secs = new ArrayList<SubprojectExtendClass>();
			return secs;
		}
		secs = subProjectInfoService.querySubProjectByID(parentid, projectid, username);
		return secs;
	}
	
	//根据projectid查询该id的详情，用于修改信息
	@RequestMapping(value="/querySubProjectByID/{projectid}/{subprojectid}",method={RequestMethod.POST,RequestMethod.GET},produces = {"application/json;charset=UTF-8"})
	public @ResponseBody SubprojectExtendClass querySubProjectByID (HttpServletRequest request, @PathVariable("projectid") Integer projectid, @PathVariable("subprojectid") Integer subprojectid) throws Exception {
		SubprojectExtendClass sec = null;
		String username = (String)request.getSession().getAttribute("username");
		if(username == null || username.trim().equals("") || projectid == null || subprojectid == null) 
		{
			sec = new SubprojectExtendClass();
			return sec;
		}
		sec = subProjectInfoService.querySubProjectByPID(subprojectid, projectid, username);
		return sec;
	}
	
	//根据subprojectid删除该目录
	@RequestMapping(value="/deleteSubProject/{projectid}/{subprojectid}/{parentid}",method={RequestMethod.POST,RequestMethod.GET},produces = {"application/json;charset=UTF-8"})
	public @ResponseBody SubProjectResult deleteSubProject(HttpServletRequest request, @PathVariable("projectid") Integer projectid, @PathVariable("subprojectid") Integer subprojectid, @PathVariable("parentid") Integer parentid) throws Exception{
		SubProjectResult spr = null;
		String username = (String)request.getSession().getAttribute("username");
		if(username == null || username.trim().equals("") || projectid == null || subprojectid == null || parentid == null) 
		{
			spr = new SubProjectResult();
			spr.setResult(false);
			spr.setMessage("用户没有登录或相关参数(如目录ID)缺失");
			return spr;
		}
		spr = subProjectInfoService.deleteSubProject(subprojectid, projectid, parentid, username);
		return spr;
	}
	
	
	//根据subprojectid更新该目录
	@RequestMapping(value="/updateSubProject",method={RequestMethod.POST,RequestMethod.GET},produces = {"application/json;charset=UTF-8"})
	public @ResponseBody SubProjectResult updateSubProject(HttpServletRequest request, @Validated(value={ValidGroup5.class,ValidGroup6.class}) SubprojectExtendClass subprojectExtendClass, BindingResult result) throws Exception {
		SubProjectResult spr = null;
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
			spr = new SubProjectResult();
			spr.setResult(false);
			spr.setMessage(errorInfos.toString());
			return spr;		
		}
		String username = (String)request.getSession().getAttribute("username");
		if(username == null || username.trim().equals("")) 
		{
			spr = new SubProjectResult();
			spr.setResult(false);
			spr.setMessage("用户没有登录");
			return spr;	
		}
		subprojectExtendClass.setSubauthorname(username);
		spr = subProjectInfoService.updateSubProject(subprojectExtendClass);
		return spr;
	}
	
	//获取各个项目的层级
	@RequestMapping(value="/getAllProject/{projectid}",method={RequestMethod.POST,RequestMethod.GET},produces = {"application/json;charset=UTF-8"})
	public @ResponseBody List<SubProjectList> getAllProject(HttpServletRequest request, @PathVariable("projectid") Integer projectid) throws Exception{
		List<SubProjectList> suls = null;
		String username = (String)request.getSession().getAttribute("username");
		if(projectid == null || username == null || username.trim().equals(""))
		{
			suls = new ArrayList<SubProjectList>();
			return suls;
		}
		suls = subProjectInfoService.queryProject(projectid, username);
		return suls;
	}
}
