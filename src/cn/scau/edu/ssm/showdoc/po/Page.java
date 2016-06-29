package cn.scau.edu.ssm.showdoc.po;

import java.util.Date;

public class Page {
    private Integer pageid;

    private Integer pageprojectid;

    private Integer pagesubprejectid;

    private String pageprojectpassword;

    private Integer pagestatu;

    private Date pagedate;

    private String pagetitle;

    private Integer pagesortid;

    private String pageauthorname;

    private String pagecontext;

    public Integer getPageid() {
        return pageid;
    }

    public void setPageid(Integer pageid) {
        this.pageid = pageid;
    }

    public Integer getPageprojectid() {
        return pageprojectid;
    }

    public void setPageprojectid(Integer pageprojectid) {
        this.pageprojectid = pageprojectid;
    }

    public Integer getPagesubprejectid() {
        return pagesubprejectid;
    }

    public void setPagesubprejectid(Integer pagesubprejectid) {
        this.pagesubprejectid = pagesubprejectid;
    }

    public String getPageprojectpassword() {
        return pageprojectpassword;
    }

    public void setPageprojectpassword(String pageprojectpassword) {
        this.pageprojectpassword = pageprojectpassword == null ? null : pageprojectpassword.trim();
    }

    public Integer getPagestatu() {
        return pagestatu;
    }

    public void setPagestatu(Integer pagestatu) {
        this.pagestatu = pagestatu;
    }

    public Date getPagedate() {
        return pagedate;
    }

    public void setPagedate(Date pagedate) {
        this.pagedate = pagedate;
    }

    public String getPagetitle() {
        return pagetitle;
    }

    public void setPagetitle(String pagetitle) {
        this.pagetitle = pagetitle == null ? null : pagetitle.trim();
    }

    public Integer getPagesortid() {
        return pagesortid;
    }

    public void setPagesortid(Integer pagesortid) {
        this.pagesortid = pagesortid;
    }

    public String getPageauthorname() {
        return pageauthorname;
    }

    public void setPageauthorname(String pageauthorname) {
        this.pageauthorname = pageauthorname == null ? null : pageauthorname.trim();
    }

    public String getPagecontext() {
        return pagecontext;
    }

    public void setPagecontext(String pagecontext) {
        this.pagecontext = pagecontext == null ? null : pagecontext.trim();
    }
}