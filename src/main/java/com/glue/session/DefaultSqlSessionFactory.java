package com.glue.session;

import com.glue.Configuration;
import com.glue.executor.Executor;
import com.glue.executor.SimpleExecutor;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author Zer01ne
 * @since 2021/7/14 0:04
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        Executor executor = new SimpleExecutor();
        return new DefaultSqlSession(configuration, executor);
    }
}
