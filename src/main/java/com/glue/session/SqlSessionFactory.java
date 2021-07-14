package com.glue.session;
/**
 * @author Zer01ne
 * @since 2021/7/14 0:03
 */
public interface SqlSessionFactory {

    /**
     * 创建SqlSession
     * @return com.glue.session.SqlSession
     */
    SqlSession openSession();

}
