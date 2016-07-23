package cn.scau.edu.ssm.showdoc.service;

import java.util.List;

import cn.scau.edu.ssm.showdoc.po.ProjectExtendClass;

public interface ProjectInfoService {
	public List<ProjectExtendClass> getProjectByName(String name) throws Exception;
	public boolean insertProject(ProjectExtendClass project) throws Exception;
	public boolean queryProjectByName(String projectname,String authorname) throws Exception;
	public boolean deleteProject(String username,Integer pid) throws Exception;
}
