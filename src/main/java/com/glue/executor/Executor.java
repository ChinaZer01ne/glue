package com.glue.executor;

import com.glue.Configuration;
import com.glue.MappedStatement;
import com.glue.session.SqlSession;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Zer01ne
 * @since 2021/7/14 0:48
 */
public interface Executor {

    /**
     * 查询
     * @param configuration : 数据库配置信息
     * @param mappedStatement : sql配置信息
     * @param params : 参数
     * @return java.util.List<E>
     */
    <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object[] params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException, IntrospectionException;
}
