package com.glue.session;

import java.util.List;

/**
 * @author Zer01ne
 * @since 2021/7/14 0:03
 */
public class DefaultSqlSession implements SqlSession {

    @Override
    public <E> List<E> selectList() {
        return null;
    }

    @Override
    public <E> E selectOne() {
        return null;
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
