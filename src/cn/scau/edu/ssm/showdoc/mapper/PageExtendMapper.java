package cn.scau.edu.ssm.showdoc.mapper;

import java.util.List;

import cn.scau.edu.ssm.showdoc.po.PageExtendClass;

public interface PageExtendMapper {
	public int insertPage(PageExtendClass pageExtendClass) throws Exception;
	public List<PageExtendClass> selectPageByPid(PageExtendClass pageExtendClass) throws Exception;
}
