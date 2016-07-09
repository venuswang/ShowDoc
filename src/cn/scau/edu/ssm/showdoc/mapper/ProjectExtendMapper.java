package cn.scau.edu.ssm.showdoc.mapper;

import java.util.List;

import cn.scau.edu.ssm.showdoc.po.ProjectExtendClass;

public interface ProjectExtendMapper {
	public List<ProjectExtendClass> queryProjectByUsername(String name) throws Exception;
	public int insertProjectSelective(ProjectExtendClass project) throws Exception;
}
