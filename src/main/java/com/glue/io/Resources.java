package com.glue.io;

import java.io.InputStream;

/**
 * 资源读取类
 *
 * @author Zer01ne
 * @since 2021/7/13 23:17
 */
public class Resources {

    /**
     * 从路径下读取资源
     * @param path : 路径
     * @return java.io.InputStream
     */
    public static InputStream getResourceAsStream(String path) {
        return Resources.class.getClassLoader().getResourceAsStream(path);
    }
}
