package cn.scau.edu.ssm.showdoc.mapper;

import cn.scau.edu.ssm.showdoc.po.Subproject;
import cn.scau.edu.ssm.showdoc.po.SubprojectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SubprojectMapper {
    int countByExample(SubprojectExample example);

    int deleteByExample(SubprojectExample example);

    int deleteByPrimaryKey(Integer subprojectid);

    int insert(Subproject record);

    int insertSelective(Subproject record);

    List<Subproject> selectByExample(SubprojectExample example);

    Subproject selectByPrimaryKey(Integer subprojectid);

    int updateByExampleSelective(@Param("record") Subproject record, @Param("example") SubprojectExample example);

    int updateByExample(@Param("record") Subproject record, @Param("example") SubprojectExample example);

    int updateByPrimaryKeySelective(Subproject record);

    int updateByPrimaryKey(Subproject record);
}