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
import org.springframework.web.bind.annotation.ResponseBody;

import cn.scau.edu.ssm.showdoc.po.PageExtendClass;
import cn.scau.edu.ssm.showdoc.po.PageResult;
import cn.scau.edu.ssm.showdoc.po.MyResultSet;
import cn.scau.edu.ssm.showdoc.service.PageInfoService;
import cn.scau.edu.ssm.showdoc.service.UserProjectService;
import cn.scau.edu.ssm.showdoc.validator.ValidGroup7;
import cn.scau.edu.ssm.showdoc.validator.ValidGroup8;

@Controller
@RequestMapping(value="/page")
public class PageInfoController {

	@Autowired
	private PageInfoService pageInfoService;
/*	@Autowired
	private UserProjectService userProjectService;*/
	
	//增加page
	@RequestMapping(value="/addPage")
	public @ResponseBody PageResult addPage(HttpServletRequest request, @Validated(value={ValidGroup7.class}) PageExtendClass pageExtendClass, BindingResult result) throws Exception {
		PageResult pr = null;
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
			pr = new PageResult();
			pr.setMessage(errorInfos.toString());
			pr.setResult(false);
			return pr;
		}
		if(pageExtendClass == null || pageExtendClass.getPageprojectid() == null)
		{
			pr = new PageResult();
			pr.setMessage("参数传递错误");
			pr.setResult(false);
			return pr;
		}
		String usname = (String)request.getSession().getAttribute("username");
		if(usname == null || usname.trim().equals(""))
		{
			pr = new PageResult();
			pr.setMessage("用户没有登录");
			pr.setResult(false);
			return pr;
		}
		pageExtendClass.setPageauthorname(usname);
		pr = pageInfoService.insertPage(pageExtendClass);
		return pr;
	}
	
	
	//根据subprojectid来查询其底下的所有page页面信息
	@RequestMapping(value="/selectPageByPid/{subprojectid}/{projectid}")
	public @ResponseBody List<PageExtendClass> selectPageByPid(HttpServletRequest request, @PathVariable("subprojectid") Integer subprojectid, @PathVariable("projectid") Integer projectid) throws Exception{
		List<PageExtendClass> pageExtendClass = null;
		if(subprojectid == null || projectid == null) {
			pageExtendClass = new ArrayList<PageExtendClass>();
			return pageExtendClass;
		}
		String usname = (String)request.getSession().getAttribute("username");
		if(usname == null || usname.trim().equals(""))
		{
			pageExtendClass = new ArrayList<PageExtendClass>();
			return pageExtendClass;
		}
		PageExtendClass pec = new PageExtendClass();
		pec.setPageauthorname(usname);
		pec.setPagesubprejectid(subprojectid);
		pec.setPageprojectid(projectid);
		pec.setPagestatu(1);
		pageExtendClass = pageInfoService.selectPages(pec);
		return pageExtendClass;
	}
	
	//根据pageid来查询相关页面信息
	@RequestMapping(value="/selectPageById/{pageid}/{projectid}")
	public @ResponseBody PageResult selectPageById(HttpServletRequest request, @PathVariable("pageid") Integer pageid, @PathVariable("projectid") Integer projectid) throws Exception {
		PageResult pr = null;
		if(pageid == null || projectid == null)
		{
			pr = new PageResult();
			pr.setMessage("页面唯一标识为空");
			pr.setResult(false);
		}
		String usname = (String)request.getSession().getAttribute("username");
		if(usname == null || usname.trim().equals(""))
		{
			pr = new PageResult();
			pr.setMessage("用户没有登录");
			pr.setResult(false);
		}
		pr = pageInfoService.selectPage(pageid, usname, projectid);
		return pr;
	}
	
	//根据pageid来删除相关页面信息
	@RequestMapping(value="/deletePageById/{pageid}/{projectid}")
	public @ResponseBody MyResultSet deletePageById(HttpServletRequest request, @PathVariable("pageid") Integer pageid, @PathVariable("projectid") Integer projectid) throws Exception {
		MyResultSet rs = null;
		if(pageid == null || projectid == null)
		{
			rs = new MyResultSet();
			rs.setMessage("页面唯一标识为空");
			rs.setResult(false);
			return rs;
		}
		String usname = (String)request.getSession().getAttribute("username");
		if(usname == null || usname.trim().equals(""))
		{
			rs = new MyResultSet();
			rs.setMessage("用户没有登录");
			rs.setResult(false);
			return rs;
		}
		rs = pageInfoService.deletePage(pageid, usname, projectid);
		return rs;
	}
	
	//更新page的相关页面信息
	@RequestMapping(value="/updatePage")
	public @ResponseBody PageResult updatePage(HttpServletRequest request, @Validated(value={ValidGroup8.class}) PageExtendClass pageExtendClass, BindingResult result) throws Exception {
		PageResult pr = null;
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
			pr = new PageResult();
			pr.setMessage(errorInfos.toString());
			pr.setResult(false);
			return pr;
		}
		if(pageExtendClass == null)
		{
			pr = new PageResult();
			pr.setMessage("参数传递错误");
			pr.setResult(false);
			return pr;
		}
		String usname = (String)request.getSession().getAttribute("username");
		if(usname == null || usname.trim().equals(""))
		{
			pr = new PageResult();
			pr.setMessage("用户没有登录");
			pr.setResult(false);
			return pr;
		}
		pageExtendClass.setPageauthorname(usname);
		pr = pageInfoService.updatePage(pageExtendClass);
		return pr;
	}
}
