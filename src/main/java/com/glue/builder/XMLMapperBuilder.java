package com.glue.builder;

import com.glue.Configuration;
import com.glue.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * mapper资源构建
 *
 * @author Zer01ne
 * @since 2021/7/13 23:42
 */
public class XMLMapperBuilder {

    private Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 解析mapper资源
     *
     * @param stream :
     */
    @SuppressWarnings("all")
    public void parse(InputStream stream) throws DocumentException {
        Document document = new SAXReader().read(stream);
        Element rootElement = document.getRootElement();
        String namespace = rootElement.attributeValue("namespace");
        List<Element> selectList = document.selectNodes("//select");

        Map<String, MappedStatement> mappedStatements = new HashMap<>();
        for (Element element : selectList) {
            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(element.attributeValue("id"));
            mappedStatement.setParameterType(element.attributeValue("parameterType"));
            mappedStatement.setResultType(element.attributeValue("resultType"));
            mappedStatement.setSql(element.getTextTrim());
            String mapperStatementId = namespace + element.attributeValue("id");
            configuration.getMappedStatements().put(mapperStatementId, mappedStatement);
        }

    }
}
