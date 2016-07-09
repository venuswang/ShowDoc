package cn.scau.edu.ssm.showdoc.po;

public class UserProject {
	private Integer pid;
	private String vname;
	
	
	
	public UserProject(Integer pid, String vname) {
		super();
		this.pid = pid;
		this.vname = vname;
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
