package cn.scau.edu.ssm.showdoc.po;

import org.hibernate.validator.constraints.NotBlank;

import cn.scau.edu.ssm.showdoc.validator.ValidGroup5;

/**
 * 这是Subproject的拓展类
 * @author Administrator
 *
 */
public class SubprojectExtendClass extends Subproject {
	@NotBlank(groups={ValidGroup5.class},message="{subproject.parentName.length.error}")
	private String parentName;

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	@Override
	public String toString() {
		return "SubprojectExtendClass [parentName=" + parentName
				+ ", getSubprojectid()=" + getSubprojectid()
				+ ", getSubprojectname()=" + getSubprojectname()
				+ ", getProjectid()=" + getProjectid() + ", getSublevel()="
				+ getSublevel() + ", getParentid()=" + getParentid()
				+ ", getSubsortid()=" + getSubsortid() + ", getSubpstatu()="
				+ getSubpstatu() + ", getSubauthorname()=" + getSubauthorname()
				+ ", getSubprojectpassword()=" + getSubprojectpassword()
				+ ", getSubdate()=" + getSubdate() + ", getHassubproject()="
				+ getHassubproject() + "]";
	}
	
}
