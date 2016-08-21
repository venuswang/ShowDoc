package cn.scau.edu.ssm.showdoc.po;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

import cn.scau.edu.ssm.showdoc.validator.ValidGroup7;
import cn.scau.edu.ssm.showdoc.validator.ValidGroup8;

public class Page {
	@Min(value=-1,groups={ValidGroup8.class},message="{page.pageid.min.error}")
    private Integer pageid;

	@Min(value=-1,groups={ValidGroup7.class,ValidGroup8.class},message="{page.pageprojectid.min.error}")
    private Integer pageprojectid;

	@Min(value=-1,groups={ValidGroup7.class,ValidGroup8.class},message="{page.pagesubprejectid.min.error}")
    private Integer pagesubprejectid;

    private String pageprojectpassword;

    private Integer pagestatu;

    private Date pagedate;

    @NotBlank(groups={ValidGroup7.class,ValidGroup8.class},message="{page.pagetitle.null.error}")
    @Size(max=200,groups={ValidGroup7.class,ValidGroup8.class},message="{page.pagetitle.length.max}")
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

	@Override
	public String toString() {
		return "Page [pageid=" + pageid + ", pageprojectid=" + pageprojectid
				+ ", pagesubprejectid=" + pagesubprejectid
				+ ", pageprojectpassword=" + pageprojectpassword
				+ ", pagestatu=" + pagestatu + ", pagedate=" + pagedate
				+ ", pagetitle=" + pagetitle + ", pagesortid=" + pagesortid
				+ ", pageauthorname=" + pageauthorname + ", pagecontext="
				+ pagecontext + "]";
	}
    
}