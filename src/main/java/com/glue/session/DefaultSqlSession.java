package com.glue.session;

import com.glue.Configuration;
import com.glue.MappedStatement;
import com.glue.executor.Executor;

import javax.sql.DataSource;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Zer01ne
 * @since 2021/7/14 0:03
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;
    /**
     * 执行器
     */
    private Executor executor;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object... params) throws SQLException, IntrospectionException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        MappedStatement mappedStatement = configuration.getMappedStatements().get(statementId);
        return executor.query(configuration, mappedStatement, params);
    }

    @SuppressWarnings("all")
    @Override
    public <E> E selectOne(String statementId, Object... params) throws SQLException, IntrospectionException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        List<Object> objects = selectList(statementId, params);
        int resultSize = objects.size();
        if (resultSize > 1) {
            throw new RuntimeException("should 1 but find " + objects.size());
        }
        return (resultSize == 0) ? null : (E) objects.get(0);
    }

    @Override
    public <E> int insert(E e) {
        return 0;
    }

    @Override
    public <E> int update(E e) {
        return 0;
    }

    @Override
    public <E> int delete(E e) {
        return 0;
    }
}
