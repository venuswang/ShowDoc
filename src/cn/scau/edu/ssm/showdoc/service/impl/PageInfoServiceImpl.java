package cn.scau.edu.ssm.showdoc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.scau.edu.ssm.showdoc.mapper.PageExtendMapper;
import cn.scau.edu.ssm.showdoc.mapper.PageMapper;
import cn.scau.edu.ssm.showdoc.po.Page;
import cn.scau.edu.ssm.showdoc.po.PageExample;
import cn.scau.edu.ssm.showdoc.po.PageExample.Criteria;
import cn.scau.edu.ssm.showdoc.po.PageExtendClass;
import cn.scau.edu.ssm.showdoc.po.PageResult;
import cn.scau.edu.ssm.showdoc.po.MyResultSet;
import cn.scau.edu.ssm.showdoc.service.PageInfoService;

public class PageInfoServiceImpl implements PageInfoService {
	@Autowired
	private PageExtendMapper pageExtendMapper;
	@Autowired
	private PageMapper pageMapper;
	
	//增加页面
	@Override
	public PageResult insertPage(PageExtendClass pageExtendClass) throws Exception {
		PageResult pr = new PageResult();
		if(pageExtendClass == null) 
		{
			pr.setMessage("传入的页面参数为空");
			pr.setResult(false);
			return pr;
		}
		pageExtendClass.setPagedate(new Date());
		int success = pageExtendMapper.insertPage(pageExtendClass);
		if(success > 0) {
			pr.setResult(true);
			pr.setPage(pageExtendClass);
		} else{
			pr.setResult(false);
			pr.setMessage("插入页面失败");
		}
		return pr;
	}

	//根据subprojectid来查询该id下的所有page界面
	@Override
	public List<PageExtendClass> selectPages(PageExtendClass pageExtendClass) throws Exception {
		List<PageExtendClass> pecs = null;
		if(pageExtendClass == null) {
			pecs = new ArrayList<PageExtendClass>();
			return pecs;
		}
		pecs = pageExtendMapper.selectPageByPid(pageExtendClass);
		return pecs;
	}

	//根据pageid查询相应的页面信息
	@Override
	public PageResult selectPage(Integer pageid, String username, Integer projectid) throws Exception {
		PageResult pr = new PageResult();
		if(pageid == null || projectid == null)
		{
			pr.setMessage("页面唯一标识不能为空");
			pr.setResult(false);
			return pr;
		}
		if(username == null || username.trim().equals(""))
		{
			pr.setMessage("用户没有登录");
			pr.setResult(false);
			return pr;
		}
		PageExample pe = new PageExample();
		Criteria criteria = pe.createCriteria();
		criteria.andPageprojectidEqualTo(projectid).andPageidEqualTo(pageid).andPagestatuEqualTo(1);
		List<Page> pages = pageMapper.selectByExampleWithBLOBs(pe);
		if(pages != null && pages.size() == 1) {
			Page page = pages.get(0);
			PageExtendClass pec = new PageExtendClass();
			BeanUtils.copyProperties(page, pec);
			pr.setResult(true);
			pr.setPage(pec);
		} else {
			pr.setMessage("该页面不存在或出现多条记录");
			pr.setResult(false);
		}
		return pr;
	}

	//删除页面
	@Override
	public MyResultSet deletePage(Integer pageid, String username, Integer projectid) throws Exception {
		MyResultSet rs = new MyResultSet();
		PageExample pe = new PageExample();
		Criteria criteria = pe.createCriteria();
		criteria.andPageprojectidEqualTo(projectid).andPageidEqualTo(pageid).andPagestatuEqualTo(1); //andPageauthornameEqualTo(username)
		Page record = new Page();
		record.setPagestatu(0);
		int succeed = pageMapper.updateByExampleSelective(record, pe);
		if(succeed > 0) {
			rs.setMessage("删除成功");
			rs.setResult(true);
		} else{
			rs.setMessage("删除失败,原因可能是该页面不存在");
			rs.setResult(false);
		}
		return rs;
	}

	
	//更新Page信息
	@Override
	public PageResult updatePage(PageExtendClass pageExtendClass) throws Exception {
		PageResult pr = new PageResult();
		if(pageExtendClass == null) 
		{
			pr.setMessage("传入的页面参数为空");
			pr.setResult(false);
			return pr;
		}
		PageExample pe = new PageExample();
		Criteria criteria = pe.createCriteria();
		criteria.andPageprojectidEqualTo(pageExtendClass.getPageprojectid()).andPageidEqualTo(pageExtendClass.getPageid()).andPagestatuEqualTo(1); //andPageauthornameEqualTo(pageExtendClass.getPageauthorname())
		int succeed = pageMapper.updateByExampleSelective(pageExtendClass, pe);
		if(succeed > 0) {
			pr.setResult(true);
			pr.setPage(pageExtendClass);
		} else {
			pr.setResult(false);
			pr.setMessage("更新失败,可能是数据库中没有该数据");
		}
		return pr;
	}
	
}
