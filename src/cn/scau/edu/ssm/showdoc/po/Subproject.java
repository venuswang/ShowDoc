package cn.scau.edu.ssm.showdoc.po;

import java.util.Date;

public class Subproject {
    private Integer subprojectid;

    private String subprojectname;

    private Integer projectid;

    private Integer sublevel;

    private Integer parentid;

    private Integer subsortid;

    private Integer subpstatu;

    private String subauthorname;

    private String subprojectpassword;

    private Date subdate;

    private Integer hassubproject;

    public Integer getSubprojectid() {
        return subprojectid;
    }

    public void setSubprojectid(Integer subprojectid) {
        this.subprojectid = subprojectid;
    }

    public String getSubprojectname() {
        return subprojectname;
    }

    public void setSubprojectname(String subprojectname) {
        this.subprojectname = subprojectname == null ? null : subprojectname.trim();
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public Integer getSublevel() {
        return sublevel;
    }

    public void setSublevel(Integer sublevel) {
        this.sublevel = sublevel;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getSubsortid() {
        return subsortid;
    }

    public void setSubsortid(Integer subsortid) {
        this.subsortid = subsortid;
    }

    public Integer getSubpstatu() {
        return subpstatu;
    }

    public void setSubpstatu(Integer subpstatu) {
        this.subpstatu = subpstatu;
    }

    public String getSubauthorname() {
        return subauthorname;
    }

    public void setSubauthorname(String subauthorname) {
        this.subauthorname = subauthorname == null ? null : subauthorname.trim();
    }

    public String getSubprojectpassword() {
        return subprojectpassword;
    }

    public void setSubprojectpassword(String subprojectpassword) {
        this.subprojectpassword = subprojectpassword == null ? null : subprojectpassword.trim();
    }

    public Date getSubdate() {
        return subdate;
    }

    public void setSubdate(Date subdate) {
        this.subdate = subdate;
    }

    public Integer getHassubproject() {
        return hassubproject;
    }

    public void setHassubproject(Integer hassubproject) {
        this.hassubproject = hassubproject;
    }
}