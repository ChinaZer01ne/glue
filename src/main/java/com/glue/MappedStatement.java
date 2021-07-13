package com.glue;
/**
 * @author Zer01ne
 * @since 2021/7/13 23:17
 */
public class MappedStatement {
    /**
     * mapper statement id
     */
    private String id;
    /**
     * 入参类型
     */
    private String parameterType;
    /**
     * 返回值类型
     */
    private String resultType;
    /**
     * sql
     */
    private String sqlText;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }
}
