package cn.scau.edu.ssm.showdoc.service.impl;

import java.util.ArrayList;
import java.util.List;

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
		if(userProject.isIsinner() == false) {
			int count = userProjectMapper.countProjectName(userProject);
			if(count != 1)
				return false;
		}
		int result = userProjectMapper.insertProjectName(userProject);
		//System.out.println(result);
		if(result > 0)
			flag = true;
		return flag;
	}

	@Override
	public boolean deleteProjectName(UserProject userProject) throws Exception {
		boolean flag = false;
		if(userProject == null || userProject.getPid() == null || userProject.getVname() == null || "".equals(userProject.getVname().trim()))
			throw new MyException("错误编号10010:userProject的字段不能为空...");
		if(userProject.isIsinner() == false) {
			int count = userProjectMapper.countProjectName(userProject);
			if(count != 1)
				return false;
		}
		int result = userProjectMapper.deleteProjectName(userProject);
		System.out.println(result);
		if(result > 0)
			flag = true;
		return flag;
	}

	@Override
	public List<UserProject> queryProjectName(UserProject userProject)
			throws Exception {
		List<UserProject> lists = null;
		if(userProject == null || userProject.getPid() == null || userProject.getVname() == null || userProject.getVname().equals(""))
			return new ArrayList<UserProject>();
		int count = userProjectMapper.countProjectName(userProject);
		if(count != 1)
			return new ArrayList<UserProject>();
		lists = userProjectMapper.queryProjectAuthorName(userProject);
		return lists;
	}
	
	
}
