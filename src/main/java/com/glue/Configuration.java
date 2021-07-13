package com.glue;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author Zer01ne
 * @since 2021/7/13 23:17
 */
public class Configuration {
    /**
     * 数据源配置信息
     */
    private DataSource dataSource;

    /**
     * sql配置信息
     */
    private Map<String, MappedStatement> mappedStatements;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MappedStatement> getMappedStatements() {
        return mappedStatements;
    }

    public void setMappedStatements(Map<String, MappedStatement> mappedStatements) {
        this.mappedStatements = mappedStatements;
    }
}
