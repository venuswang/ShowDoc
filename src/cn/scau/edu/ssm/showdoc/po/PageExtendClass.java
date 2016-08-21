package cn.scau.edu.ssm.showdoc.po;
/**
 * 这是Page的拓展类
 * @author Administrator
 *
 */
public class PageExtendClass extends Page {

	@Override
	public String toString() {
		return "PageExtendClass [getPageid()=" + getPageid()
				+ ", getPageprojectid()=" + getPageprojectid()
				+ ", getPagesubprejectid()=" + getPagesubprejectid()
				+ ", getPageprojectpassword()=" + getPageprojectpassword()
				+ ", getPagestatu()=" + getPagestatu() + ", getPagedate()="
				+ getPagedate() + ", getPagetitle()=" + getPagetitle()
				+ ", getPagesortid()=" + getPagesortid()
				+ ", getPageauthorname()=" + getPageauthorname()
				+ ", getPagecontext()=" + getPagecontext() + "]";
	}
	
}
