package com.chen.dao.mapper1;

import com.chen.dao.vo1.ChenTest;
import com.chen.dao.vo1.ChenTestExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChenTestMapper {
    long countByExample(ChenTestExample example);

    int deleteByExample(ChenTestExample example);

    int insert(ChenTest record);

    int insertSelective(ChenTest record);

    List<ChenTest> selectByExample(ChenTestExample example);

    int updateByExampleSelective(@Param("record") ChenTest record, @Param("example") ChenTestExample example);

    int updateByExample(@Param("record") ChenTest record, @Param("example") ChenTestExample example);
}