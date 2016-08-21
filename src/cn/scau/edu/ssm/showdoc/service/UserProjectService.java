package cn.scau.edu.ssm.showdoc.service;

import java.util.List;

import cn.scau.edu.ssm.showdoc.po.UserProject;

public interface UserProjectService {
	
	public boolean insertProjectName(UserProject userProject) throws Exception;
	
	public boolean deleteProjectName(UserProject userProject) throws Exception;
	
	public List<UserProject> queryProjectName(UserProject userProject) throws Exception;
}
