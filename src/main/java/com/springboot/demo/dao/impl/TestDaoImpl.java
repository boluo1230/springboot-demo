package com.springboot.demo.dao.impl;

import com.springboot.demo.dao.TestDao;
import com.springboot.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author : LIUBOLUN
 * @date : 2019-03-23
 */
@Repository
public class TestDaoImpl implements TestDao {

    @Autowired
    private UserMapper userMapper;
}
