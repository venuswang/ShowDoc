package cn.scau.edu.ssm.showdoc.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.scau.edu.ssm.showdoc.po.UserProject;
import cn.scau.edu.ssm.showdoc.service.UserProjectService;

public class UserProjectServiceImplTest {

	private ApplicationContext applicationContext;
	
	//在setUp这个方法得到spring容器
	@Before
	public void setUp() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
	}

	//测试方法  insertProjectName
	@Test
	public void testInsertProjectName() throws Exception {
		UserProjectService userProjectService = (UserProjectService) applicationContext.getBean("userProjectService");
		//调用UserProjectService的方法添加项目协作者
		UserProject userProject = new UserProject();
		userProject.setPid(2); //设置项目的id为2
		userProject.setVname("xiaodong"); //添加项目的协作者为"xiaodong"
		userProject.setRealname("tencent"); //添加项目的作者为"tencent"
		boolean is_succeed = userProjectService.insertProjectName(userProject);
		assert(is_succeed);
	}

	//测试方法  deleteProjectName
	@Test
	public void testDeleteProjectName() throws Exception {
		UserProjectService userProjectService = (UserProjectService) applicationContext.getBean("userProjectService");
		//调用UserProjectService的方法删除项目协作者
		UserProject userProject = new UserProject();
		userProject.setPid(2); //设置项目的id为2
		userProject.setVname("xiaodong"); //添加项目的协作者为"xiaodong"
		userProject.setRealname("tencent"); //添加项目的作者为"tencent"
		boolean is_succeed = userProjectService.deleteProjectName(userProject);
		assert(is_succeed);
	}

	//测试方法  queryProjectName
	@Test
	public void testQueryProjectName() throws Exception {
		UserProjectService userProjectService = (UserProjectService) applicationContext.getBean("userProjectService");
		//调用UserProjectService的方法查询项目协作者
		UserProject userProject = new UserProject();
		userProject.setPid(4); //设置项目的id为4
		userProject.setVname("tencent"); //添加项目的协作者为"tencent"
		userProject.setRealname("tencent");
		List<UserProject> lists = userProjectService.queryProjectName(userProject);
		for(UserProject list : lists)
			System.out.println("协作者名字:"+list.getVname());
	}

}
