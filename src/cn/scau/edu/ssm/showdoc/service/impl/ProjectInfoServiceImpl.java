package cn.scau.edu.ssm.showdoc.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.scau.edu.ssm.showdoc.exception.MyException;
import cn.scau.edu.ssm.showdoc.mapper.ProjectExtendMapper;
import cn.scau.edu.ssm.showdoc.mapper.ProjectMapper;
import cn.scau.edu.ssm.showdoc.po.ProjectExample;
import cn.scau.edu.ssm.showdoc.po.ProjectExample.Criteria;
import cn.scau.edu.ssm.showdoc.po.ProjectExtendClass;
import cn.scau.edu.ssm.showdoc.po.UserProject;
import cn.scau.edu.ssm.showdoc.service.ProjectInfoService;
import cn.scau.edu.ssm.showdoc.service.UserProjectService;

public class ProjectInfoServiceImpl implements ProjectInfoService {
	@Autowired
	private ProjectExtendMapper projectExtendMapper;
	@Autowired
	private UserProjectService userProjectService;
	@Autowired
	private ProjectMapper projectMapper;
	
	@Override
	public List<ProjectExtendClass> getProjectByName(String name) throws Exception {
		if(name == null || "".equals(name.trim()))
			throw new MyException("错误编号10009:用户名为空...");
		List<ProjectExtendClass> pecs = projectExtendMapper.queryProjectByUsername(name);
		return pecs;
	}

	@Override
	public boolean insertProject(ProjectExtendClass project) throws Exception {
		boolean flag = false;
		boolean check = this.queryProjectByName(project.getProjectname(), project.getAuthorname());
		if(check == false)
			throw new MyException("错误编号10013:服务器出错(新建项目时该项目已经被该用户创建)...");
		project.setPdate(new Date());
		int result = projectExtendMapper.insertProjectSelective(project);
		if(result == 0 || project == null)
			throw new MyException("错误编号10011:服务器出错(创建新项目时)...");
		UserProject userProject = new UserProject(project.getId(),project.getAuthorname());
		flag = userProjectService.insertProjectName(userProject);
		return flag;
	}

	@Override
	public boolean queryProjectByName(String projectname, String authorname)
			throws Exception {
		boolean flag = false;
		if(projectname == null || authorname == null || projectname.trim().equals("") || authorname.trim().equals(""))
			throw new MyException("错误编号10012:服务器出错(查询项目是否已经创建过时)...");
		ProjectExample example = new ProjectExample();
		Criteria criteria = example.createCriteria();
		criteria.andAuthornameEqualTo(authorname).andProjectdescEqualTo(projectname).andPstatuEqualTo(1);
		int result = projectMapper.countByExample(example);
		if(result == 0)
			flag = true;
		return flag;
	}

}
