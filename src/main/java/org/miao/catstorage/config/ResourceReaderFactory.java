/*
 * Copyright 2013 Miao.
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
 * @(#)ResourceReaderFactory.java    0.8 01/25/2013
 */
package org.miao.catstorage.config;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.miao.catstorage.exception.StorageException;

/**
 * 资源读取工厂
 * @author Miao
 * @version 0.8
 * @since 0.8
 */
public class ResourceReaderFactory {
    
    /**
     * 日志
     */
    private static final Log log = LogFactory.getLog(ResourceReaderFactory.class);
    
    /**
     * 构建资源读取对象
     * @param resources 资源文件数组
     * @return 资源读取对象
     * @throws StorageException 如果资源文件数组参数为空抛出此异常
     * @since 0.8
     */
    public static ResourceReader build(File[] resources) {
        if (resources != null) {
            return build(Arrays.asList(resources));
        } else {
            log.error("资源文件不得为空");
            throw new StorageException("资源文件不得为空");
        }
    }
    
    /**
     * 构建资源读取对象
     * @param resource 资源文件
     * @return 资源读取对象
     * @throws StorageException 如果资源文件参数为空抛出此异常
     * @since 0.8
     */
    public static ResourceReader bulid(File resource) {
        if (resource != null) {
            return build(new File[]{resource});
        } else {
            log.error("资源文件不得为空");
            throw new StorageException("资源文件不得为空");
        }
    }
    
    /**
     * 构建资源读取对象
     * @param resources 资源文件定位符数组
     * @return 资源读取对象
     * @throws StorageException 如果资源文件数组参数为空抛出此异常
     * @since 0.8
     */
    public static ResourceReader build(String[] resources) {
        if (resources != null || resources.length > 0) {
            List<File> files = new ArrayList<File>(resources.length);
            for(String resource : resources) {
                files.add(new File(resource));
            }
            return build(files);
        } else {
            log.error("资源文件不得为空");
            throw new StorageException("资源文件不得为空");
        }
    }
    
    /**
     * 构建资源读取对象
     * @param resource 资源文件定位符
     * @return 资源读取对象
     * @throws StorageException 如果资源文件参数为空抛出此异常
     * @since 0.8
     */
    public static ResourceReader build(String resource) {
        if (StringUtils.isNotEmpty(resource)) {
            return build(new String[]{resource});
        } else {
            log.error("资源文件不得为空");
            throw new StorageException("资源文件不得为空");
        }
    }
    
    private static ResourceReader build(List<File> resources) {
        return new JsonResourceReader(resources);
    }
}
