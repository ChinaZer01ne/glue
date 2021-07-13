package com.glue.session;

/**
 * @author Zer01ne
 * @since 2021/7/14 0:03
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build() {
        return new DefaultSqlSessionFactory();
    }
}
