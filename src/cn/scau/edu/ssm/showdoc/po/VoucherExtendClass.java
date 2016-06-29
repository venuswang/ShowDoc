package cn.scau.edu.ssm.showdoc.po;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import cn.scau.edu.ssm.showdoc.validator.ValidGroup1;
import cn.scau.edu.ssm.showdoc.validator.ValidGroup3;

/**
 * 这是Voucher的拓展类
 * @author Administrator
 *
 */
public class VoucherExtendClass extends Voucher {
    @NotBlank(message="{voucher.password.null.error}",groups={ValidGroup3.class})
	@Size(min=6,max=50,message="{voucher.password.length.error}",groups={ValidGroup3.class}) //
	private String newPassword;
	private Integer number;
	
	
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
}
