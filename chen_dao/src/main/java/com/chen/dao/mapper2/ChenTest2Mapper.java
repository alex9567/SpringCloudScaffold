package com.chen.dao.mapper2;

import com.chen.dao.vo2.ChenTest2;
import com.chen.dao.vo2.ChenTest2Example;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChenTest2Mapper {
    long countByExample(ChenTest2Example example);

    int deleteByExample(ChenTest2Example example);

    int insert(ChenTest2 record);

    int insertSelective(ChenTest2 record);

    List<ChenTest2> selectByExample(ChenTest2Example example);

    int updateByExampleSelective(@Param("record") ChenTest2 record, @Param("example") ChenTest2Example example);

    int updateByExample(@Param("record") ChenTest2 record, @Param("example") ChenTest2Example example);
}