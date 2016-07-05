package cn.scau.edu.ssm.showdoc.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.scau.edu.ssm.showdoc.exception.MyException;
import cn.scau.edu.ssm.showdoc.mapper.VoucherExtendMapper;
import cn.scau.edu.ssm.showdoc.mapper.VoucherInfoMapper;
import cn.scau.edu.ssm.showdoc.mapper.VoucherMapper;
import cn.scau.edu.ssm.showdoc.po.Voucher;
import cn.scau.edu.ssm.showdoc.po.VoucherExample;
import cn.scau.edu.ssm.showdoc.po.VoucherExample.Criteria;
import cn.scau.edu.ssm.showdoc.po.VoucherExtendClass;
import cn.scau.edu.ssm.showdoc.po.VoucherInfoExtendClass;
import cn.scau.edu.ssm.showdoc.po.VoucherVO;
import cn.scau.edu.ssm.showdoc.service.VoucherInfoService;

public class VoucherInfoServiceImpl implements VoucherInfoService {
	@Autowired
	private VoucherExtendMapper voucherExtendMapper;
	
	@Autowired
	private VoucherInfoMapper voucherInfoMapper;
	
	@Autowired
	private VoucherMapper voucherMapper;
	
	/**
	 * 用户账户信息注册动作
	 */
	@Override
	public Integer insertVoucherInfo(VoucherVO voucherVO) throws Exception {
		VoucherExtendClass record = voucherVO.getVoucher();
		VoucherInfoExtendClass vif = voucherVO.getVoucherInfo();
		Integer resultId = this.queryVoucherByName(record.getUsername());
		if(resultId == 1)
			throw new MyException("错误编号10004:用户账号已经存在数据库中...");
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
				sb.append(skill).append('-');
			}
			if(sb != null && sb.length() > 0)
				sb.deleteCharAt(sb.length()-1);
			vif.setSkill(sb.toString());
		}
		vif.setRegistdate(new Date());
		voucherInfoMapper.insertSelective(vif);
		return voucherid;
	}

	/**
	 * 用于前端异步检验用户名是否已经注册
	 */
	@Override
	public Integer queryVoucherByName(String username) throws Exception {
		VoucherExample example = new VoucherExample();
		Criteria criteral = example.createCriteria();
		criteral.andUsernameEqualTo(username);
		List<Voucher> vouchers = voucherMapper.selectByExample(example);
		if(vouchers == null || vouchers.size() == 0)
			return 0;  //此账号可以注册
		else
			return 1;  //此账号已经注册过
	}
	
	/**
	 * 通过用户ID、username、password来更新用户的账户信息,成功返回true
	 */
	@Override
	public boolean updateById(int id, VoucherExtendClass voucher) throws Exception {
		boolean updateSuccess = true;
		Integer resultId = voucherExtendMapper.selectVoucherExtend(voucher);
		if(resultId == null || id != resultId)
			updateSuccess = false;
		voucherExtendMapper.updateVoucherExtend(voucher);
		return updateSuccess;
	}

	/**
	 * 检验用户登录，需要用户名和密码
	 */
	@Override
	public Integer selectVoucherByNameAndPass(VoucherExtendClass voucher)
			throws Exception {
		VoucherExample example = new VoucherExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(voucher.getUsername()).andPasswordEqualTo(voucher.getPassword()).andStatuEqualTo(1);
		List<Voucher> vouchers = voucherMapper.selectByExample(example);
		if(vouchers == null || vouchers.size() == 0 || vouchers.size() > 1)
			return null;
		else
			return vouchers.get(0).getId();
	}

	/**
	 * 通过ID来查询用户的信息
	 */
	@Override
	public VoucherVO queryVoucherById(Integer id) throws Exception {
		if(id == null)
			throw new MyException("错误编号10005:用户信息ID为空...");
		//通过ID查询Voucher
		Voucher voucher = voucherMapper.selectByPrimaryKey(id);
		if(voucher == null)
			throw new MyException("错误编号10006:用户不存在数据库中...");
		//通过ID查询VoucherInfo
//		VoucherInfo voucherInfo = voucherInfoMapper.selectByPrimaryKey(id);
		VoucherVO voucherVO = new VoucherVO();
		VoucherExtendClass voucherExtend = new VoucherExtendClass();
		BeanUtils.copyProperties(voucher, voucherExtend);
		VoucherInfoExtendClass voucherInfoExtend = voucherInfoMapper.selectExtendByPrimaryKey(id);
//		if(voucherInfo != null)
//		{
//			voucherInfoExtend = new VoucherInfoExtendClass();
//			BeanUtils.copyProperties(voucherInfo, voucherInfoExtend);
//		}
		voucherVO.setVoucher(voucherExtend);
		voucherVO.setVoucherInfo(voucherInfoExtend);
		return voucherVO;
	}

	@Override
	public boolean updateVoucherInfoById(Integer id, VoucherInfoExtendClass voucherInfo) throws Exception
	{
		if(id == null)
			throw new MyException("错误编号10007:用户唯一标识出错...");
		boolean flag = true;
		if(voucherInfo.getSkills() != null && voucherInfo.getSkills().length > 0)
		{
			StringBuffer sb = new StringBuffer();
			for(String skill : voucherInfo.getSkills())
			{
				sb.append(skill).append('-');
			}
			if(sb != null && sb.length() > 0)
				sb.deleteCharAt(sb.length()-1);
			voucherInfo.setSkill(sb.toString());
		}
		int result = voucherInfoMapper.updateExtendByPrimaryKeySelective(voucherInfo);
		if(result == 0)
			flag = false;
		return flag;
	}

	//通过ID来查询picture
	public String queryPictureById(Integer id) throws Exception{
		if(id == null)
			throw new MyException("错误编号10008:用户信息唯一标识出错...");
		String picResult = voucherExtendMapper.queryPictureById(id);
		return picResult;
	}
}
