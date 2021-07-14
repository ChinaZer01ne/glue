package com.glue.session;

import com.glue.Configuration;
import com.glue.builder.XMLConfigBuilder;
import com.glue.io.Resources;
import org.dom4j.DocumentException;

import java.io.InputStream;

/**
 * @author Zer01ne
 * @since 2021/7/14 0:03
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream inputStream) throws DocumentException {
        Configuration configuration = new XMLConfigBuilder().parseConfig(inputStream);
        return new DefaultSqlSessionFactory(configuration);
    }
}
