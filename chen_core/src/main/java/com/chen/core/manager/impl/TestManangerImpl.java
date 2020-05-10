package com.chen.core.manager.impl;

import com.alibaba.fastjson.JSON;
import com.chen.common.exception.BaseException;
import com.chen.common.logAop.ParamsLog;
import com.chen.core.manager.TestMananger;
import com.chen.dao.mapper1.ChenTestMapper;
import com.chen.dao.mapper2.ChenTest2Mapper;
import com.chen.dao.vo1.ChenTest;
import com.chen.dao.vo1.ChenTestExample;
import com.chen.dao.vo2.ChenTest2;
import com.chen.dao.vo2.ChenTest2Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author chen
 */
@Service
@Slf4j
public class TestManangerImpl implements TestMananger {
    @Resource
    ChenTestMapper chenTestMapper;
    @Resource
    ChenTest2Mapper chenTest2Mapper;

    @Override
    public String getOne() {
        ChenTestExample example = new ChenTestExample();
        ChenTestExample.Criteria criteria = example.createCriteria();
        Long result = chenTestMapper.countByExample(example);
        log.info("result1:"+result);
        return "1";
    }

    @Override
    public String getTwo() {
        ChenTest2Example example = new ChenTest2Example();
        ChenTest2Example.Criteria criteria = example.createCriteria();
        Long result = chenTest2Mapper.countByExample(example);
        log.info("result2:"+result);
        return "2";
    }

    @Override
    public String getOneByPage() {
        ChenTestExample example = new ChenTestExample();
        ChenTestExample.Criteria criteria = example.createCriteria();
        PageHelper.startPage(1, 5);
        List<ChenTest> list = chenTestMapper.selectByExample(example);
        PageInfo<ChenTest> pageInfo = new PageInfo<>(list);
        log.info("list1:{}", JSON.toJSONString(list));
        log.info("page1:{}",JSON.toJSONString(pageInfo));
        return "byPage1";
    }

    @Override
    public String getTwoByPage() {
        ChenTest2Example example = new ChenTest2Example();
        ChenTest2Example.Criteria criteria = example.createCriteria();
        PageHelper.startPage(1, 5);
        List<ChenTest2> list = chenTest2Mapper.selectByExample(example);
        PageInfo<ChenTest2> pageInfo = new PageInfo<>(list);
        log.info("list1:{}", JSON.toJSONString(list));
        log.info("page2:{}",JSON.toJSONString(pageInfo));
        return "byPage2";
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String insertOne() {
        log.info("manager#insertOne");
        ChenTest chenTest = new ChenTest();
        chenTest.setGmtCreate(LocalDateTime.now());
        chenTest.setGmtModified(LocalDateTime.now());
        chenTest.setName("chentest1");
        chenTest.setNum(5);
        int result = chenTestMapper.insertSelective(chenTest);
        ChenTest2 chenTest2 = new ChenTest2();
        chenTest2.setGmtCreate(LocalDateTime.now());
        chenTest2.setGmtModified(LocalDateTime.now());
        chenTest2.setName("chentest2");
        chenTest2.setNum(6);
        int result2 = chenTest2Mapper.insertSelective(chenTest2);
        if(1==1){
            throw new BaseException("test4","1111","11111");
        }
        int error = 0;
        int b = 1/error;
        return "1";
    }

    @Override
    public String insertTwo() {
        ChenTest2 chenTest2 = new ChenTest2();
        chenTest2.setGmtCreate(LocalDateTime.now());
        chenTest2.setGmtModified(LocalDateTime.now());
        chenTest2.setName("chentest2");
        chenTest2.setNum(6);
        int result = chenTest2Mapper.insertSelective(chenTest2);
        int error = 0;
        int b = 1/error;
        return "2";
    }
}
