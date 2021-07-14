package com.glue.session;

import com.glue.MappedStatement;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Zer01ne
 * @since 2021/7/14 0:03
 */
public interface SqlSession {
    /**
     * 查询集合
     * @param statementId :
     * @param params : 参数
     * @return java.util.List<E>
     */
    <E> List<E> selectList(String statementId, Object... params) throws SQLException, IntrospectionException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException;
    /**
     * 单个查询
     * @param statementId :
     * @param params :
     * @return E
     */
    <E> E selectOne(String statementId, Object... params) throws SQLException, IntrospectionException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException;
    /**
     * 插入
     * @param e :
     * @return int
     */
    <E> int insert(E e);
    /**
     * 更新
     * @param e :
     * @return int
     */
    <E> int update(E e);
    /**
     * 删除
     * @param e :
     * @return int
     */
    <E> int delete(E e);

}
