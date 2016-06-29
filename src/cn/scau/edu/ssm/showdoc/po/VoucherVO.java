package cn.scau.edu.ssm.showdoc.po;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import cn.scau.edu.ssm.showdoc.validator.ValidGroup1;

/**
 * 创建用户信息展示类VoucherVO
 * 
 * @author Administrator
 * 
 */
public class VoucherVO {
	
	@NotNull(message="{voucherVO.voucherExtendClass.null.error}",groups={ValidGroup1.class})
	@Valid
	private VoucherExtendClass voucher;
	
	@NotNull(message="{voucherVO.voucherInfoExtendClass.null.error}",groups={ValidGroup1.class})
	@Valid
	private VoucherInfoExtendClass voucherInfo;

	public VoucherExtendClass getVoucher() {
		return voucher;
	}

	public void setVoucher(VoucherExtendClass voucher) {
		this.voucher = voucher;
	}

	public VoucherInfoExtendClass getVoucherInfo() {
		return voucherInfo;
	}

	public void setVoucherInfo(VoucherInfoExtendClass voucherInfo) {
		this.voucherInfo = voucherInfo;
	}

}
