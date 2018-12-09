package com.qin.mapper.simple;

import com.qin.model.simple.ProductDate;
import com.qin.model.simple.ProductDateExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDateMapper {
    int countByExample(ProductDateExample example);

    int deleteByExample(ProductDateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductDate record);

    int insertSelective(ProductDate record);

    List<ProductDate> selectByExample(ProductDateExample example);

    ProductDate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductDate record, @Param("example") ProductDateExample example);

    int updateByExample(@Param("record") ProductDate record, @Param("example") ProductDateExample example);

    int updateByPrimaryKeySelective(ProductDate record);

    int updateByPrimaryKey(ProductDate record);
}