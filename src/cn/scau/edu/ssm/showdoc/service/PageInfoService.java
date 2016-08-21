package cn.scau.edu.ssm.showdoc.service;

import java.util.List;

import cn.scau.edu.ssm.showdoc.po.PageExtendClass;
import cn.scau.edu.ssm.showdoc.po.PageResult;
import cn.scau.edu.ssm.showdoc.po.MyResultSet;

public interface PageInfoService {
	public PageResult insertPage(PageExtendClass pageExtendClass) throws Exception;
	public List<PageExtendClass> selectPages(PageExtendClass pageExtendClass) throws Exception;
	public PageResult selectPage(Integer pageid, String username, Integer projectid) throws Exception;
	public MyResultSet deletePage(Integer pageid, String username, Integer projectid) throws Exception;
	public PageResult updatePage(PageExtendClass pageExtendClass) throws Exception;
}
