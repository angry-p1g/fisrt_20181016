package com.gq.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import javax.annotation.Resource;

/**
 * 高版本取消了自动注入SqlSessionDaoSupport
 */
public class CommonDao extends SqlSessionDaoSupport {

        @Override
        @Resource
        public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
            super.setSqlSessionFactory(sqlSessionFactory);
        }
}
