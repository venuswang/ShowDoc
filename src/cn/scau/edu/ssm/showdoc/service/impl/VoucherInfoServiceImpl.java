package cn.scau.edu.ssm.showdoc.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import cn.scau.edu.ssm.showdoc.exception.MyException;
import cn.scau.edu.ssm.showdoc.mapper.VoucherExtendMapper;
import cn.scau.edu.ssm.showdoc.mapper.VoucherInfoMapper;
import cn.scau.edu.ssm.showdoc.mapper.VoucherMapper;
import cn.scau.edu.ssm.showdoc.po.VoucherExtendClass;
import cn.scau.edu.ssm.showdoc.po.VoucherInfoExtendClass;
import cn.scau.edu.ssm.showdoc.po.VoucherVO;
import cn.scau.edu.ssm.showdoc.service.VoucherInfoService;

public class VoucherInfoServiceImpl implements VoucherInfoService {
	@Autowired
	private VoucherExtendMapper voucherExtendMapper;
	
	@Autowired
	private VoucherInfoMapper voucherInfoMapper; 
	
	@Override
	public Integer insertVoucherInfo(VoucherVO voucherVO) throws Exception {
		VoucherExtendClass record = voucherVO.getVoucher();
		VoucherInfoExtendClass vif = voucherVO.getVoucherInfo();
		voucherExtendMapper.insertSelectiveExtend(record);
		Integer voucherid = record.getId();
		if(voucherid == null)
			throw new MyException("错误编号10003:用户账号和密码插入数据库出错...");
		vif.setVoucherid(voucherid);
		if(vif.getSkills() != null && vif.getSkills().length > 0)
		{
			StringBuffer sb = new StringBuffer();
			for(String skill : vif.getSkills())
			{
				sb.append(skill);
			}
			vif.setSkill(sb.toString());
		}
		vif.setRegistdate(new Date());
		voucherInfoMapper.insertSelective(vif);
		return voucherid;
	}

}
