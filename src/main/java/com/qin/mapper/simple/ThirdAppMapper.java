package com.qin.mapper.simple;

import com.qin.model.simple.ThirdApp;
import com.qin.model.simple.ThirdAppExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ThirdAppMapper {
    int countByExample(ThirdAppExample example);

    int deleteByExample(ThirdAppExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ThirdApp record);

    int insertSelective(ThirdApp record);

    List<ThirdApp> selectByExample(ThirdAppExample example);

    ThirdApp selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ThirdApp record, @Param("example") ThirdAppExample example);

    int updateByExample(@Param("record") ThirdApp record, @Param("example") ThirdAppExample example);

    int updateByPrimaryKeySelective(ThirdApp record);

    int updateByPrimaryKey(ThirdApp record);
}