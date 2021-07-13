package com.glue.builder;

import com.glue.Configuration;
import com.glue.io.Resources;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * 配置构建
 *
 * @author Zer01ne
 * @since 2021/7/13 23:19
 */
public class XMLConfigBuilder {

    /**
     * 构建的配置信息类
     */
    private final Configuration configuration;

    public XMLConfigBuilder() {
        configuration = new Configuration();
    }

    @SuppressWarnings("all")
    public Configuration build(InputStream inputStream) throws DocumentException {
        Document document = new SAXReader().read(inputStream);
        Element configurationElement = document.getRootElement();
        List<Element> list = configurationElement.selectNodes("//property");

        Properties properties = new Properties();
        for (Element element : list) {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.put(name, value);
        }

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(properties.getProperty("url"));
        config.setDriverClassName(properties.getProperty("driverClass"));
        config.setUsername(properties.getProperty("user"));
        config.setPassword(properties.getProperty("password"));
        HikariDataSource dataSource = new HikariDataSource(config);

        configuration.setDataSource(dataSource);

        List<Element> mapperElementList = configurationElement.selectNodes("//mapper");
        for (Element element : mapperElementList) {
            XMLMapperBuilder mapperBuilder = new XMLMapperBuilder(configuration);
            String resource = element.attributeValue("resource");
            InputStream stream = Resources.getResourceAsStream(resource);
            mapperBuilder.parse(stream);
        }

        return configuration;
    }
}
