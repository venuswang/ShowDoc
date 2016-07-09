package cn.scau.edu.ssm.showdoc.mapper;

import cn.scau.edu.ssm.showdoc.po.UserProject;

/**
 * 数据库表username_project的Mapper
 * @author Administrator
 *
 */
public interface UserProjectMapper {
	
	public int insertProjectName(UserProject userProject) throws Exception;
	
	public int deleteProjectName(UserProject userProject) throws Exception;
	
}
