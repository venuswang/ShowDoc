package cn.scau.edu.ssm.showdoc.po;
/**
 * 这是用于展示下拉框(数据是某项目下的所有文件夹的名称)的po类
 * @author Administrator
 *
 */
public class SubProjectList {
	private Integer subProjectId;
	private String subProjectName;
	private Integer nextLevel;
	
	public Integer getSubProjectId() {
		return subProjectId;
	}
	
	public void setSubProjectId(Integer subProjectId) {
		this.subProjectId = subProjectId;
	}
	
	public String getSubProjectName() {
		return subProjectName;
	}
	
	public void setSubProjectName(String subProjectName) {
		this.subProjectName = subProjectName;
	}
	
	public Integer getNextLevel() {
		return nextLevel;
	}
	
	public void setNextLevel(Integer nextLevel) {
		this.nextLevel = nextLevel;
	}

	@Override
	public String toString() {
		return "SubProjectList [subProjectId=" + subProjectId
				+ ", subProjectName=" + subProjectName + ", nextLevel="
				+ nextLevel + "]";
	}
	
}
