package cn.scau.edu.ssm.showdoc.po;

public class UserProject {
	private Integer pid;
	private String vname;
	private String realname;
	private boolean isinner;

	public UserProject(Integer pid, String vname, String realname,boolean isinner) {
		super();
		this.pid = pid;
		this.vname = vname;
		this.realname = realname;
		this.isinner = isinner;
	}
	
	public boolean isIsinner() {
		return isinner;
	}

	public void setIsinner(boolean isinner) {
		this.isinner = isinner;
	}

	public String getRealname() {
		return realname;
	}
	
	public void setRealname(String realname) {
		this.realname = realname;
	}
	
	public UserProject(){
		
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

}
