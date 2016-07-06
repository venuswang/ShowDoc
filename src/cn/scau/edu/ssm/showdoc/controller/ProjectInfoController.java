package cn.scau.edu.ssm.showdoc.controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 创建project的控制类
 * @author Administrator
 *
 */
@RequestMapping("/project")
public class ProjectInfoController {
	
	@RequestMapping(value="/showProject.action")
	public String queryProject() throws Exception{
		return "forward:/project/userproject.jsp";
	}
	
}
