package cn.scau.edu.ssm.showdoc.mapper;

import java.util.List;

import cn.scau.edu.ssm.showdoc.po.UserProject;

/**
 * 数据库表username_project的Mapper
 * @author Administrator
 *
 */
public interface UserProjectMapper {
	
	public int insertProjectName(UserProject userProject) throws Exception;
	
	public int deleteProjectName(UserProject userProject) throws Exception;
	
	public int countProjectName(UserProject userProject) throws Exception;
	
	public List<UserProject> queryProjectAuthorName(UserProject userProject) throws Exception;
}
