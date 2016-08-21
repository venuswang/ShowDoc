package cn.scau.edu.ssm.showdoc.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.scau.edu.ssm.showdoc.po.MyResultSet;
import cn.scau.edu.ssm.showdoc.po.PageExtendClass;
import cn.scau.edu.ssm.showdoc.po.PageResult;
import cn.scau.edu.ssm.showdoc.service.PageInfoService;

public class PageInfoServiceImplTest {
	
	private ApplicationContext applicationContext;
	private PageInfoService pageInfoService;
	
	@Before
	public void setUp() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		pageInfoService = (PageInfoService)applicationContext.getBean("pageInfoService");
	}

	//增加页面
	@Test
	public void testInsertPage() throws Exception {
		PageExtendClass pec = new PageExtendClass();
		pec.setPageprojectid(4);
		pec.setPagesubprejectid(1);
		pec.setPagetitle("测试页面");
		pec.setPageauthorname("tencent");
		pec.setPagecontext("这是我在腾讯公司这边的测试...");
		PageResult pr = pageInfoService.insertPage(pec);
		assert(pr.isResult() == true);
	}

	//根据subprojectid来查询该id下的所有page界面
	@Test
	public void testSelectPages() throws Exception {
		PageExtendClass pec = new PageExtendClass();
		pec.setPageauthorname("tencent");
		pec.setPagesubprejectid(1);
		pec.setPagestatu(1);
		List<PageExtendClass> lists = pageInfoService.selectPages(pec);
		assert(lists.size() == 1);
		for(PageExtendClass list : lists)
			System.out.println(list);
	}

	//根据pageid查询相应的页面信息
	@Test
	public void testSelectPage() throws Exception {
		Integer pageid = 1;
		String username = "tencent";
		Integer projectid = 4;
		PageResult pr = pageInfoService.selectPage(pageid, username, projectid);
		assert(pr.isResult() == true);
		System.out.println(pr.getPage());
	}

	//删除页面
	@Test
	public void testDeletePage() throws Exception {
		Integer pageid = 1;
		String username = "tencent";
		Integer projectid = 4; 
		MyResultSet mrs = pageInfoService.deletePage(pageid, username, projectid);
		assert(mrs.isResult() == true);
	}

	//更新Page信息
	@Test
	public void testUpdatePage() throws Exception {
		PageExtendClass pec = new PageExtendClass();
		pec.setPageprojectid(4);
		pec.setPagesubprejectid(1);
		pec.setPagetitle("测试页面--update");
		pec.setPageauthorname("tencent");
		pec.setPagecontext("这是我在腾讯公司这边的测试(update)...");
		pec.setPageid(1);
		PageResult pr = pageInfoService.updatePage(pec);
		assert(pr.isResult() == true);
		System.out.println(pr.getPage());
	}

}
