package cn.scau.edu.ssm.showdoc.po;

import java.util.Date;

public class Project {
    private Integer id;

    private String projectname;

    private String projectdesc;

    private String authorname;

    private String projectpassword;

    private Integer sortid;

    private Integer pstatu;

    private Date pdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname == null ? null : projectname.trim();
    }

    public String getProjectdesc() {
        return projectdesc;
    }

    public void setProjectdesc(String projectdesc) {
        this.projectdesc = projectdesc == null ? null : projectdesc.trim();
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname == null ? null : authorname.trim();
    }

    public String getProjectpassword() {
        return projectpassword;
    }

    public void setProjectpassword(String projectpassword) {
        this.projectpassword = projectpassword == null ? null : projectpassword.trim();
    }

    public Integer getSortid() {
        return sortid;
    }

    public void setSortid(Integer sortid) {
        this.sortid = sortid;
    }

    public Integer getPstatu() {
        return pstatu;
    }

    public void setPstatu(Integer pstatu) {
        this.pstatu = pstatu;
    }

    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }
}