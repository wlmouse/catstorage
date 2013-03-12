/*
 * Copyright 2011 Miao.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @(#)JsonResourceReader.java    0.8 01/25/2013
 */
package org.miao.catstorage.config;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.miao.catstorage.exception.StorageException;

/**
 * JSON格式资源读取
 * @author Miao
 * @version 0.8
 * @since 0.7
 */
class JsonResourceReader implements ResourceReader {
    
    /**
     * 资源文件
     */
    private List<File> resources = Collections.emptyList();
    /**
     * 日志
     */
    private static final Log log = LogFactory.getLog(JsonResourceReader.class);

    /**
     * 构造方法
     * @param resources 资源文件列表
     * @throws StorageException 如果resources参数为空抛出此异常
     * @since 0.8
     */
    JsonResourceReader(List<File> resources) {
        if (resources != null && !resources.isEmpty()) {
            this.resources = resources;
        } else {
            throw new StorageException("资源文件不得为空");
        }
    }

    /**
     * 获取资源流列表
     * @return 资源流列表
     * @since 0.7
     */
    private List<Reader> getReader() {
        List<Reader> readers = new ArrayList<Reader>(resources.size());
        for (File file : resources) {
            try {
                readers.add(new BufferedReader(new FileReader(file)));
            } catch (FileNotFoundException ex) {
                log.error("未找到资源文件" + file, ex);
                closeReaders(readers);
                throw new StorageException("未找到资源文件" + file, ex);
            }
        }
        return readers;
    }

    /**
     * 关闭资源流
     * @param readers 资源流列表
     * @since 0.7
     */
    private void closeReaders(List<Reader> readers) {
        try {
            for (Reader reader : readers) {
                reader.close();
            }
        } catch (IOException ex) {
            log.error("资源流无法关闭", ex);
            throw new StorageException(ex);
        }
    }

    /**
     * 获取资源
     * @param reader 读取流
     * @param type 类型
     * @return 资源
     * @since 0.7
     */
    private Map<String, String> read(Reader reader, Type type) {
        return (Map<String, String>) new Gson().fromJson(reader, type);
    }

    /**
     * 获取类型
     * @return 类型
     * @since 0.7
     */
    private Type getType() {
        return new TypeToken<HashMap<String, String>>() {
        }.getType();
    }

    public Map<String, String> read() {
        Map<String, String> statementMap = new HashMap<String, String>();
        List<Reader> readers = getReader();
        Type type = getType();
        try {
            for (Reader reader : readers) {
                statementMap.putAll(read(reader, type));
            }
            return statementMap;
        } finally {
            closeReaders(readers);
        }
    }
}
