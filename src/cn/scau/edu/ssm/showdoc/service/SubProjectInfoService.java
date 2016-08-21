package cn.scau.edu.ssm.showdoc.service;

import java.util.List;

import cn.scau.edu.ssm.showdoc.po.SubProjectList;
import cn.scau.edu.ssm.showdoc.po.SubProjectResult;
import cn.scau.edu.ssm.showdoc.po.SubprojectExtendClass;

public interface SubProjectInfoService {
	public SubProjectResult insertSubProject(SubprojectExtendClass spec) throws Exception;
	public List<SubprojectExtendClass> querySubProjectByID(Integer id, Integer projectId, String username) throws Exception;
	public SubprojectExtendClass querySubProjectByPID(Integer subProjectId, Integer projectId, String username) throws Exception;
	public SubProjectResult deleteSubProject(Integer subProjectId, Integer projectId, Integer parentid, String username) throws Exception;
	public SubProjectResult updateSubProject(SubprojectExtendClass spec) throws Exception;
	public List<SubProjectList> queryProject(Integer projectId, String username) throws Exception;
}
