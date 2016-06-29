package cn.scau.edu.ssm.showdoc.po;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import cn.scau.edu.ssm.showdoc.validator.ValidGroup1;

/**
 * 这是VoucherInfo的拓展类
 * @author Administrator
 *
 */
public class VoucherInfoExtendClass extends VoucherInfo {
	
	@NotBlank(groups={ValidGroup1.class},message="{voucherInfo.mail.isnull.error}")  //
	@Email(groups={ValidGroup1.class},message="{voucherInfo.mail.ismail.error}")  //
	@Pattern(regexp="\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*",groups={ValidGroup1.class},message="{voucherInfo.mail.ismail.error}")
	private String email;
	
	private String[] skills;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String[] getSkills() {
		return skills;
	}

	public void setSkills(String[] skills) {
		this.skills = skills;
	}
	
	
}
