package cn.scau.edu.ssm.showdoc.po;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import cn.scau.edu.ssm.showdoc.validator.ValidGroup1;

public class Voucher {
    private Integer id;

    @NotBlank(message="{voucher.name.null.error}",groups={ValidGroup1.class})
    @Size(min=6,max=15,message="{voucher.name.length.error}",groups={ValidGroup1.class})  //
    private String username;

    @NotBlank(message="{voucher.password.null.error}",groups={ValidGroup1.class})
    @Size(min=6,max=50,message="{voucher.password.length.error}",groups={ValidGroup1.class}) //
    private String password;

    private Integer statu;

    private Integer loginmethod;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getStatu() {
        return statu;
    }

    public void setStatu(Integer statu) {
        this.statu = statu;
    }

    public Integer getLoginmethod() {
        return loginmethod;
    }

    public void setLoginmethod(Integer loginmethod) {
        this.loginmethod = loginmethod;
    }
}