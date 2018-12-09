package com.qin.mapper.simple;

import com.qin.model.simple.ThemeProductExample;
import com.qin.model.simple.ThemeProductKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ThemeProductMapper {
    int countByExample(ThemeProductExample example);

    int deleteByExample(ThemeProductExample example);

    int deleteByPrimaryKey(ThemeProductKey key);

    int insert(ThemeProductKey record);

    int insertSelective(ThemeProductKey record);

    List<ThemeProductKey> selectByExample(ThemeProductExample example);

    int updateByExampleSelective(@Param("record") ThemeProductKey record, @Param("example") ThemeProductExample example);

    int updateByExample(@Param("record") ThemeProductKey record, @Param("example") ThemeProductExample example);
}