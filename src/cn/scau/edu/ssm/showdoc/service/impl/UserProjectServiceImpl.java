package cn.scau.edu.ssm.showdoc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import cn.scau.edu.ssm.showdoc.exception.MyException;
import cn.scau.edu.ssm.showdoc.mapper.UserProjectMapper;
import cn.scau.edu.ssm.showdoc.po.UserProject;
import cn.scau.edu.ssm.showdoc.service.UserProjectService;

public class UserProjectServiceImpl implements UserProjectService {
	@Autowired
	private UserProjectMapper userProjectMapper; 
	
	@Override
	public boolean insertProjectName(UserProject userProject) throws Exception {
		boolean flag = false;
		if(userProject == null || userProject.getPid() == null || userProject.getVname() == null || "".equals(userProject.getVname().trim()))
			throw new MyException("错误编号10010:userProject的字段不能为空...");
		int result = userProjectMapper.insertProjectName(userProject);
		System.out.println(result);
		if(result > 0)
			flag = true;
		return flag;
	}

	@Override
	public boolean deleteProjectName(UserProject userProject) throws Exception {
		boolean flag = false;
		if(userProject == null || userProject.getPid() == null || userProject.getVname() == null || "".equals(userProject.getVname().trim()))
			throw new MyException("错误编号10010:userProject的字段不能为空...");
		int result = userProjectMapper.deleteProjectName(userProject);
		System.out.println(result);
		if(result > 0)
			flag = true;
		return flag;
	}

}
