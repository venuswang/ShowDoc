package cn.scau.edu.ssm.showdoc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.scau.edu.ssm.showdoc.exception.MyException;
import cn.scau.edu.ssm.showdoc.mapper.PageMapper;
import cn.scau.edu.ssm.showdoc.mapper.SubProjectExtendMapper;
import cn.scau.edu.ssm.showdoc.mapper.SubprojectMapper;
import cn.scau.edu.ssm.showdoc.po.Page;
import cn.scau.edu.ssm.showdoc.po.PageExample;
import cn.scau.edu.ssm.showdoc.po.SubProjectList;
import cn.scau.edu.ssm.showdoc.po.SubProjectResult;
import cn.scau.edu.ssm.showdoc.po.Subproject;
import cn.scau.edu.ssm.showdoc.po.SubprojectExample;
import cn.scau.edu.ssm.showdoc.po.SubprojectExample.Criteria;
import cn.scau.edu.ssm.showdoc.po.SubprojectExtendClass;
import cn.scau.edu.ssm.showdoc.service.SubProjectInfoService;

public class SubProjectInfoServiceImpl implements SubProjectInfoService {
	@Autowired
	private SubprojectMapper subProjectMapper;
	@Autowired
	private SubProjectExtendMapper subProjectExtendMapper;
	@Autowired
	private PageMapper pageMapper;
	
	//添加项目下的目录
	@Override
	public SubProjectResult insertSubProject(SubprojectExtendClass spec) throws Exception {
		boolean isSucceed = false;
		if(spec == null)
			throw new MyException("错误编号10014:创建项目下的目录时,传入的目录信息为空...");
		SubProjectResult spr = new SubProjectResult();
		String parentName = "";
		spec.setSubdate(new Date());
		spec.setHassubproject(0);
		int successNum = subProjectExtendMapper.insertSubProjectSelective(spec);      //subProjectMapper.insertSelective(spec);
		if(successNum > 0) {
			if(spec.getParentid() != -1) {
				Subproject record = new Subproject();
				record.setSubprojectid(spec.getParentid());
				record.setHassubproject(1);
				int updateResult = subProjectMapper.updateByPrimaryKeySelective(record);
				if(updateResult > 0) {
					isSucceed = true;
					parentName = spec.getParentName() + "/";
					//Subproject sp = subProjectMapper.selectByPrimaryKey(spec.getParentid());
					//if(sp != null) {
					//	parentName = sp.getSubprojectname() + "/";
					//}
				}
			} else{
				isSucceed = true;
			}
		}
		if(isSucceed == true) {
			SubProjectList spl = new SubProjectList();
			spl.setNextLevel(spec.getSublevel()+1);
			spl.setSubProjectId(spec.getSubprojectid());
			spl.setSubProjectName(parentName + spec.getSubprojectname());
			spr.setSul(spl);
		} else{
			spr.setMessage("服务器内部错误,请重试");
		}
		spr.setResult(isSucceed);
		return spr;
	}

	//查询某id下的目录,-1表示加载父目录下的目录名
	@Override
	public List<SubprojectExtendClass> querySubProjectByID(Integer parentId, Integer projectId, String username) throws Exception {
		List<SubprojectExtendClass> secs = new ArrayList<SubprojectExtendClass>();
		SubprojectExample subprojectExample = new SubprojectExample();
		Criteria criteria = subprojectExample.createCriteria();
		criteria.andParentidEqualTo(parentId).andProjectidEqualTo(projectId).andSubpstatuEqualTo(1);  //.andSubauthornameEqualTo(username)
		List<Subproject> subprojects = subProjectMapper.selectByExample(subprojectExample);
		if(subprojects != null && subprojects.size() > 0) 
		{
			for(Subproject subproject: subprojects)
			{
				SubprojectExtendClass sec = new SubprojectExtendClass();
				BeanUtils.copyProperties(subproject, sec);
				secs.add(sec);
			}
		} 
		return secs;
	}

	//根据projectid来查询该目录的详细信息
	@Override
	public SubprojectExtendClass querySubProjectByPID(Integer subProjectId, Integer projectId, String username) throws Exception {
		SubprojectExtendClass spec = null;
		if(subProjectId == null || projectId == null || username == null || username.trim().equals(""))
		{
			spec = new SubprojectExtendClass();
			return spec;
		}
		SubprojectExample subprojectExample = new SubprojectExample();
		Criteria criteria = subprojectExample.createCriteria();
		criteria.andSubprojectidEqualTo(subProjectId).andProjectidEqualTo(projectId).andSubpstatuEqualTo(1); //.andSubauthornameEqualTo(username)
		List<Subproject> subprojects = subProjectMapper.selectByExample(subprojectExample);
		if(subprojects != null && subprojects.size() > 0){
			spec = new SubprojectExtendClass();
			BeanUtils.copyProperties(subprojects.get(0), spec);
		} else{
			spec = new SubprojectExtendClass();
		}
		return spec;
	}

	
	//删除某文件夹
	@Override
	public SubProjectResult deleteSubProject(Integer subProjectId, Integer projectId, Integer parentid, String username) throws Exception {
		SubProjectResult spr = null;
		//参数校验
		if(username == null || username.trim().equals("") || projectId == null || subProjectId == null || parentid == null) 
		{
			spr = new SubProjectResult();
			spr.setResult(false);
			spr.setMessage("用户没有登录或相关参数(如目录ID)缺失");
			return spr;
		}
		//根据subprojectid更新相关记录
		SubprojectExample subprojectExample = new SubprojectExample();
		Criteria criteria = subprojectExample.createCriteria();
		criteria.andSubprojectidEqualTo(subProjectId).andParentidEqualTo(parentid).andProjectidEqualTo(projectId).andSubpstatuEqualTo(1); //.andSubauthornameEqualTo(username)
		Subproject sp = new Subproject();
		sp.setSubpstatu(0);
		int updateResult = subProjectMapper.updateByExampleSelective(sp, subprojectExample);
		if(updateResult < 1)
		{
			spr = new SubProjectResult();
			spr.setResult(false);
			spr.setMessage("该记录不存在");
			return spr;
		}
		//设置parentid的hassubproject字段
		if(parentid != -1)
		{
			SubprojectExample se = new SubprojectExample();
			Criteria ct = se.createCriteria();
			ct.andParentidEqualTo(parentid).andProjectidEqualTo(projectId).andSubpstatuEqualTo(1);  //.andSubauthornameEqualTo(username)
			int count = subProjectMapper.countByExample(se);
			if(count == 0) {
				Subproject record = new Subproject();
				record.setSubprojectid(parentid);
				record.setHassubproject(0);
				subProjectMapper.updateByPrimaryKeySelective(record);
			}
		}
		PageExample example = new PageExample();
		cn.scau.edu.ssm.showdoc.po.PageExample.Criteria pageCriteria = example.createCriteria();
		pageCriteria.andPageprojectidEqualTo(projectId).andPagesubprejectidEqualTo(subProjectId).andPagestatuEqualTo(1);
		Page pageRecord = new Page();
		pageRecord.setPagestatu(0);
		pageMapper.updateByExampleSelective(pageRecord, example);
		spr = new SubProjectResult();
		spr.setResult(true);
		spr.setMessage("删除成功");
		return spr;
	}

	//更新目录信息
	@Override
	public SubProjectResult updateSubProject(SubprojectExtendClass spec) throws Exception {
		SubProjectResult spr = new SubProjectResult();
		if(spec == null || spec.getSubprojectid() == null)
		{
			spr.setResult(false);
			spr.setMessage("目录ID为空");
			return spr;
		}
		Subproject sp = subProjectMapper.selectByPrimaryKey(spec.getSubprojectid());
		if(sp.getParentid() != spec.getParentid()) {
			//更新原始parentid的hassubproject状态
			SubprojectExample se = new SubprojectExample();
			Criteria ct = se.createCriteria();
			ct.andParentidEqualTo(sp.getParentid()).andProjectidEqualTo(spec.getProjectid()).andSubpstatuEqualTo(1); //.andSubauthornameEqualTo(spec.getSubauthorname())
			int count = subProjectMapper.countByExample(se);
			if(count == 1) {
				Subproject record = new Subproject();
				record.setSubprojectid(sp.getParentid());
				record.setHassubproject(0);
				subProjectMapper.updateByPrimaryKeySelective(record);
			}
			//更新现在parentid的hassubproject状态
			Subproject record1 = new Subproject();
			record1.setSubprojectid(spec.getParentid());
			record1.setHassubproject(1);
			subProjectMapper.updateByPrimaryKeySelective(record1);
		}
		
		//更新该目录的信息
		int updateResult = subProjectMapper.updateByPrimaryKeySelective(spec);
		if(updateResult < 1){
			spr.setMessage("更新失败,可能是该目录不是您所创建的目录");
			spr.setResult(false);
		}else{
			spr.setResult(true);
		}
		return spr;
	}

	//获取所有的目录名和对应的下一层级、及subprojectid
	@Override
	public List<SubProjectList> queryProject(Integer projectId, String username) throws Exception {
		List<SubProjectList> spls = new ArrayList<SubProjectList>();
		SubProjectList spl = null;
		spl = new SubProjectList();
		spl.setNextLevel(0);
		spl.setSubProjectId(-1);
		spl.setSubProjectName("无");
		spls.add(spl);
		for(int i = 0; i < spls.size(); i++) {
			int parentid = spls.get(i).getSubProjectId();
			String name = spls.get(i).getSubProjectName();
			int level = spls.get(i).getNextLevel();
			SubprojectExample subprojectExample = new SubprojectExample();
			Criteria criteria = subprojectExample.createCriteria();
			criteria.andParentidEqualTo(parentid).andProjectidEqualTo(projectId).andSubpstatuEqualTo(1);  //.andSubauthornameEqualTo(username)
			List<Subproject> subprojects = subProjectMapper.selectByExample(subprojectExample);
			if(subprojects != null && subprojects.size() > 0) {
				for(Subproject subproject: subprojects) {
					spl = new SubProjectList();
					spl.setNextLevel(level+1);
					spl.setSubProjectId(subproject.getSubprojectid());
					if(parentid == -1) {
						spl.setSubProjectName(subproject.getSubprojectname());
					} else{
						spl.setSubProjectName(name + "/" + subproject.getSubprojectname());
					}
					spls.add(spl);
				}
			}
		}
		return spls;
	}

}
