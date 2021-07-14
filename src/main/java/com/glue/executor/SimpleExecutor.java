package com.glue.executor;

import com.glue.Configuration;
import com.glue.MappedStatement;
import com.glue.mapping.BoundSql;
import com.glue.mapping.ParameterMapping;
import com.glue.parsing.GenericTokenParser;
import com.glue.parsing.ParameterMappingTokenHandler;

import javax.sql.DataSource;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 基本执行器
 * @author Zer01ne
 * @since 2021/7/14 23:25
 */
public class SimpleExecutor implements Executor{

    @SuppressWarnings("all")
    @Override
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object[] params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException, IntrospectionException {
        // 获取连接
        Connection connection = configuration.getDataSource().getConnection();
        String sqlText = mappedStatement.getSql();

        // 获取处理后的sql和参数
        BoundSql boundSql = getBoundSql(sqlText);

        // 获取预处理对象
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSql());

        // 设置参数
        String parameterType = mappedStatement.getParameterType();

        Class<?> aClass = getClassType(parameterType);
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        for (int i = 0; i < parameterMappings.size(); i++) {
            ParameterMapping parameterMapping = parameterMappings.get(i);
            String content = parameterMapping.getContent();
            Field field = aClass.getDeclaredField(content);
            field.setAccessible(true);
            Object o = field.get(params[0]);
            preparedStatement.setObject(i + 1, o);
        }

        // 执行sql，封装结果集
        ResultSet resultSet = preparedStatement.executeQuery();

        String resultType = mappedStatement.getResultType();
        Class<?> resultClass = getClassType(resultType);
        Object o = resultClass.getConstructor().newInstance();
        List<Object> res = new ArrayList<>();
        while (resultSet.next()) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                String columnName = metaData.getColumnName(i);
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(o, resultSet.getObject(columnName));
            }
            res.add(o);
        }

        return (List<E>) res;
    }

    private Class<?> getClassType(String parameterType) throws ClassNotFoundException {
        if (parameterType == null) {
            return null;
        }
        return Class.forName(parameterType);
    }

    /**
     * 解析sql成可识别的sql
     * @param sqlText :
     * @return com.glue.mapping.BoundSql
     */
    private BoundSql getBoundSql(String sqlText) {
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{","}",parameterMappingTokenHandler);
        // 解析后的sql
        String sql = genericTokenParser.parse(sqlText);
        // 解析出的参数
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();
        return new BoundSql(sql, parameterMappings);
    }
}
