package com.glue.mapping;

import java.util.List;

/**
 * @author Zer01ne
 * @since 2021/7/14 23:32
 */
public class BoundSql {

    /**
     * sql
     */
    private String sql;
    /**
     * 参数
     */
    private List<ParameterMapping> parameterMappings;

    public BoundSql(String sql, List<ParameterMapping> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void setParameterMappings(List<ParameterMapping> parameterMappings) {
        this.parameterMappings = parameterMappings;
    }
}
