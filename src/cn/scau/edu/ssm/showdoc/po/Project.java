package cn.scau.edu.ssm.showdoc.po;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import cn.scau.edu.ssm.showdoc.validator.ValidGroup4;

public class Project {
    private Integer id;
    
    @NotBlank(message="{project.name.null.error}",groups={ValidGroup4.class})
    @Size(max=200,message="{project.name.length.error}",groups={ValidGroup4.class})
    private String projectname;

    @Size(max=200,message="{project.desc.length.error}",groups={ValidGroup4.class}) 
    private String projectdesc;

    @NotBlank(message="{project.authorname.null.error}",groups={ValidGroup4.class})
    @Size(min=6,max=15,message="{project.authorname.length.error}",groups={ValidGroup4.class})
    private String authorname;
    
    @Size(max=50,message="{project.password.length.error}",groups={ValidGroup4.class}) 
    private String projectpassword;

    @Max(value=99,message="{project.sortid.length.error}",groups={ValidGroup4.class})
    @Min(value=0,message="{project.sortid.length.error}",groups={ValidGroup4.class})
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