package cn.scau.edu.ssm.showdoc.mapper;

import cn.scau.edu.ssm.showdoc.po.Voucher;

/**
 * 创建用户账号和密码的拓展类
 * @author Administrator
 *
 */
public interface VoucherExtendMapper {
	public void insertSelectiveExtend(Voucher voucher) throws Exception;
}
