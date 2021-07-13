package com.glue.session;

import java.util.List;

/**
 * @author Zer01ne
 * @since 2021/7/14 0:03
 */
public interface SqlSession {
    /**
     * 查询集合
     * @return java.util.List<E>
     */
    <E> List<E> selectList();
    /**
     * 单个查询
     * @return E
     */
    <E> E selectOne();
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
