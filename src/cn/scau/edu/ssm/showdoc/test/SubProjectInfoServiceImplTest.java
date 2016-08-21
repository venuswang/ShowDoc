package cn.scau.edu.ssm.showdoc.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.scau.edu.ssm.showdoc.po.SubProjectList;
import cn.scau.edu.ssm.showdoc.po.SubProjectResult;
import cn.scau.edu.ssm.showdoc.po.SubprojectExtendClass;
import cn.scau.edu.ssm.showdoc.service.SubProjectInfoService;

public class SubProjectInfoServiceImplTest {
	
	private ApplicationContext applicationContext;
	private SubProjectInfoService subProjectInfoService;
	
	@Before
	public void setUp() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		subProjectInfoService = (SubProjectInfoService)applicationContext.getBean("subProjectInfoService");
	}

	//添加项目下的目录
	@Test
	public void testInsertSubProject() throws Exception {
		SubprojectExtendClass subprojectExtendClass = new SubprojectExtendClass();
		subprojectExtendClass.setParentName("这是父级目录--update"); //表示是项目下的父级目录
		subprojectExtendClass.setSubprojectname("这是子级目录");
		subprojectExtendClass.setProjectid(4);
		subprojectExtendClass.setSublevel(1);
		subprojectExtendClass.setParentid(1);//表示是项目下的父级目录
		subprojectExtendClass.setSubauthorname("tencent");
		SubProjectResult result = subProjectInfoService.insertSubProject(subprojectExtendClass);
		assert(result.isResult());
		System.out.println(result.getSul());
	}

	//查询某id下的目录,-1表示加载父目录下的目录名
	@Test
	public void testQuerySubProjectByID() throws Exception {
		Integer projectId = 4;
		Integer parentId = -1;
		String username = "tencent";
		List<SubprojectExtendClass> lists = subProjectInfoService.querySubProjectByID(parentId, projectId, username);
		assert(lists.size()==1);
		for(SubprojectExtendClass list : lists){
			System.out.println(list);
		}
	}

	//根据projectid来查询该目录的详细信息
	@Test
	public void testQuerySubProjectByPID() throws Exception {
		Integer projectId = 4;
		Integer subProjectId = 1;
		String username = "tencent";
		SubprojectExtendClass list = subProjectInfoService.querySubProjectByPID(subProjectId, projectId, username);
		assert(list.getSubprojectid()==1);
		System.out.println(list);
	}

	//删除某文件夹
	@Test
	public void testDeleteSubProject() throws Exception {
		Integer projectId = 4;
		Integer subProjectId = 2;
		String username = "tencent";
		Integer parentid = 1;
		SubProjectResult spr = subProjectInfoService.deleteSubProject(subProjectId, projectId, parentid, username);
		assert(spr.isResult() == true);
	}

	//更新目录信息
	@Test
	public void testUpdateSubProject() throws Exception {
		SubprojectExtendClass subprojectExtendClass = new SubprojectExtendClass();
		subprojectExtendClass.setParentName("无"); //表示是项目下的父级目录
		subprojectExtendClass.setSubprojectname("这是父级目录--update");
		subprojectExtendClass.setProjectid(4);
		subprojectExtendClass.setSublevel(0);
		subprojectExtendClass.setParentid(-1);//表示是项目下的父级目录
		subprojectExtendClass.setSubprojectid(1);
		subprojectExtendClass.setSubauthorname("tencent");
		SubProjectResult spr = subProjectInfoService.updateSubProject(subprojectExtendClass);
		assert(spr.isResult() == true);
	}

	//获取所有的目录名和对应的下一层级、及subprojectid
	@Test
	public void testQueryProject() throws Exception {
		Integer projectId = 4;
		String username = "tencent";
		List<SubProjectList> lists = subProjectInfoService.queryProject(projectId, username);
		assert(2==lists.size());
		for(SubProjectList list : lists)
			System.out.println(list);
	}

}
