package cn.scau.edu.ssm.showdoc.mapper;

import cn.scau.edu.ssm.showdoc.po.VoucherInfo;
import cn.scau.edu.ssm.showdoc.po.VoucherInfoExample;
import cn.scau.edu.ssm.showdoc.po.VoucherInfoExtendClass;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface VoucherInfoMapper {
    int countByExample(VoucherInfoExample example);

    int deleteByExample(VoucherInfoExample example);

    int deleteByPrimaryKey(Integer voucherid);

    int insert(VoucherInfo record);

    int insertSelective(VoucherInfoExtendClass record);

    List<VoucherInfo> selectByExample(VoucherInfoExample example);

    VoucherInfo selectByPrimaryKey(Integer voucherid);
    
    VoucherInfoExtendClass selectExtendByPrimaryKey(Integer voucherid);

    int updateByExampleSelective(@Param("record") VoucherInfo record, @Param("example") VoucherInfoExample example);

    int updateByExample(@Param("record") VoucherInfo record, @Param("example") VoucherInfoExample example);

    int updateByPrimaryKeySelective(VoucherInfo record);
    
    int updateExtendByPrimaryKeySelective(VoucherInfoExtendClass record);

    int updateByPrimaryKey(VoucherInfo record);
}