package com.mybatis.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by renwujie on 2018/05/13 at 13:05
 */
public class MyBatisUtil {

    private static SqlSessionFactory sqlSessionFactory = null;

    static {
        String resource = "mybatis.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            //SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, "developmentDemo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSession() {
        return sqlSessionFactory.openSession();
    }

    public static void closeSession(SqlSession session) {
        session.close();
    }

}
