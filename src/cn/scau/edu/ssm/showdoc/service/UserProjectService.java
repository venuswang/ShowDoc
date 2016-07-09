package cn.scau.edu.ssm.showdoc.service;

import cn.scau.edu.ssm.showdoc.po.UserProject;

public interface UserProjectService {
	
	public boolean insertProjectName(UserProject userProject) throws Exception;
	
	public boolean deleteProjectName(UserProject userProject) throws Exception;
	
}
