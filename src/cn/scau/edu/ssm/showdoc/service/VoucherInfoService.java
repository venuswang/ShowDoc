package cn.scau.edu.ssm.showdoc.service;

import cn.scau.edu.ssm.showdoc.po.VoucherExtendClass;
import cn.scau.edu.ssm.showdoc.po.VoucherInfoExtendClass;
import cn.scau.edu.ssm.showdoc.po.VoucherVO;

public interface VoucherInfoService {
	
	public Integer insertVoucherInfo(VoucherVO voucherVO) throws Exception;
	
	public Integer queryVoucherByName(String username) throws Exception;
	
	public boolean updateById(int id,VoucherExtendClass voucher) throws Exception;
	
	public Integer selectVoucherByNameAndPass(VoucherExtendClass voucher) throws Exception;
	
	public VoucherVO queryVoucherById(Integer id) throws Exception;
	
	public boolean updateVoucherInfoById(Integer id,VoucherInfoExtendClass voucherInfo) throws Exception;
	
	public String queryPictureById(Integer id) throws Exception;
}
