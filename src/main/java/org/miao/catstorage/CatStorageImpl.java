/*
 *  Copyright 2009 Miao.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 *
 *  @(#)CatStorage.java    0.8 01/28/2013
 */
package org.miao.catstorage;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.miao.catstorage.config.ResourceReader;
import org.miao.catstorage.config.ResourceReaderFactory;
import org.miao.catstorage.core.Filter;
import org.miao.catstorage.core.FilterFactory;
import org.miao.catstorage.exception.StorageException;

/**
 * 仓库猫实现
 * @author Miao
 * @version 0.8
 * @since 0.1
 */
public class CatStorageImpl implements CatStorage {

    /**
     * 资源Reader
     */
    private ResourceReader configReader;
    /**
     * 预定义语句Map
     */
    private Map<String, String> statementMap;
    /**
     * 过滤器
     */
    private Filter filter;
    /**
     * 日志
     */
    private static final Log log = LogFactory.getLog(CatStorageImpl.class);
    
    /**
     * 缺省构造方法
     * @since 0.8
     */
    private CatStorageImpl() {
    }

    /**
     * @throws StorageException 如果未找到指定的语句，则抛出此异常
     */
    public Result get(String key) {
        String statement = statementMap.get(key);
        if (StringUtils.isNotEmpty(statement)) {
            return new Result(statement);
        } else {
            log.error("Key:" + key + "的预定义语句未找到");
            throw new StorageException("Key:" + key + "的预定义语句未找到");
        }
    }

    /**
     * @throws StorageException 如果未找到指定的语句，则抛出此异常
     */
    public Result get(String key, List<String> parameters) {
        String statement = statementMap.get(key);
        if (StringUtils.isNotEmpty(statement)) {
            return new Result(filter(statement, parameters));
        } else {
            log.error("Key:" + key + "的预定义语句未找到");
            throw new StorageException("Key:" + key + "的预定义语句未找到");
        }
    }

    /**
     * @throws StorageException 如果未找到指定的语句，则抛出此异常
     */
    public Result get(String key, Map<String, Object> parameters) {
        return get(key, getParameterList(parameters));
    }

    /**
     * 预定义语句过滤
     * @param statement 预定义语句
     * @param parameters 查询参数
     * @return 处理好的语句
     * @since 0.1
     */
    private String filter(String statement, List<String> parameters) {
        return filter.filter(statement, parameters != null ? parameters : new ArrayList<String>(0));
    }

    /**
     * 根据参数Map生成参数列表
     * @param parameters 参数Map
     * @return 参数列表
     * @since 0.1
     */
    private List<String> getParameterList(Map<String, Object> parameters) {
        List<String> parameterList = new ArrayList<String>();
        if (parameters != null) {
            Iterator<Entry<String, Object>> iterator = parameters.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, Object> entry = iterator.next();
                if (entry.getValue() != null) {
                    parameterList.add(entry.getKey());
                }
            }
        }
        return parameterList;
    }
    
    /**
     * 设置资源文件
     * @param resource 资源文件
     * @since 0.8
     */
    public void setResource(File resource) {
        configReader = ResourceReaderFactory.bulid(resource);
    }

    /**
     * 设置资源文件
     * @param resources 资源文件数组
     * @since 0.8
     */
    public void setResource(File[] resources) {
        configReader = ResourceReaderFactory.build(resources);
    }

    /**
     * 根据资源文件路径名字符串设置资源文件
     *
     * @param resource 路径名字符串
     * @since 0.8
     */
    public void setResource(String resource) {
        configReader = ResourceReaderFactory.build(resource);
    }

    /**
     * 根据资源文件路径名字符串设置资源文件
     * @param resources 路径名字符串数组
     * @since 0.8
     */
    public void setResource(String[] resources) {
        configReader = ResourceReaderFactory.build(resources);
    }
    
    /**
     * 设置过滤器
     * @param filter 过滤器
     * @since 0.8
     */
    public void setFilter(Filter filter) {
        if(filter != null) {
            this.filter = filter;
        }
    }
    
    public void flush() {
        statementMap = configReader.read();
    }

    /**
     * 仓库猫构造方法
     * @param resource 资源文件
     * @since 0.8
     */
    public static CatStorage getInstance(final File resource) {
        return new CatStorageFactory() {

            @Override
            public void setResource(CatStorageImpl catstorage) {
                catstorage.setResource(resource);
            }
        }.getCatStorage();
    }

    /**
     * 仓库猫构造方法
     * @param resources 资源文件数组
     * @since 0.8
     */
    public static CatStorage getInstance(final File[] resources) {
        return new CatStorageFactory() {

            @Override
            public void setResource(CatStorageImpl catstorage) {
                catstorage.setResource(resources);
            }
        }.getCatStorage();
    }

    /**
     * 仓库猫构造方法
     * @param resource 资源文件定位符
     * @since 0.8
     */
    public static CatStorage getInstance(final String resource) {
        return new CatStorageFactory() {

            @Override
            public void setResource(CatStorageImpl catstorage) {
                catstorage.setResource(resource);
            }
        }.getCatStorage();
    }

    /**
     * 仓库猫构造方法
     * @param resources 资源文件定位符数组
     * @since 0.8
     */
    public static CatStorage getInstance(final String[] resources) {
        return new CatStorageFactory() {

            @Override
            public void setResource(CatStorageImpl catstorage) {
                catstorage.setResource(resources);
            }
        }.getCatStorage();
    }
    
    /**
     * 仓库猫实现抽象构造工厂内部模板类
     * @since 0.8
     */
    private abstract static class CatStorageFactory {
        /**
         * 设置资源
         * @param catstorage 仓库猫实现
         * @since 0.8
         */
        abstract void setResource(CatStorageImpl catstorage);

        /**
         * 获取仓库猫实现
         * @return 仓库猫实现
         * @since 0.8
         */
        CatStorage getCatStorage() {
            CatStorageImpl catstorage = new CatStorageImpl();
            setResource(catstorage);
            catstorage.setFilter(FilterFactory.getFilter());
            catstorage.flush();
            log.info("仓库猫构建完成，开始提供服务");
            return catstorage;
        }
    }
}
