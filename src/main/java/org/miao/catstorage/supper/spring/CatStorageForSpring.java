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
 * @(#)Result.java    0.8 01/28/2013
 */
package org.miao.catstorage.supper.spring;

import java.io.File;
import java.util.List;
import java.util.Map;
import org.miao.catstorage.CatStorage;
import org.miao.catstorage.CatStorageImpl;
import org.miao.catstorage.Result;
import org.springframework.beans.factory.InitializingBean;

/**
 * Spring框架支持对象
 * <p>仓库猫对Spring框架的整合对象，用来无缝集成仓库猫和Spring。</p>
 * @author Miao
 * @version 0.8
 * @since 0.7
 */
public class CatStorageForSpring implements CatStorage, InitializingBean {
    
    /**
     * 仓库猫实例
     */
    private CatStorage catstorage;
    /**
     * 资源文件
     */
    private File[] resources;

    public Result get(String key) {
        return catstorage.get(key);
    }
    
    public Result get(String key, List<String> parameters) {
        return catstorage.get(key, parameters);
    }

    public Result get(String key, Map<String, Object> parameters) {
        return catstorage.get(key, parameters);
    }

    public void flush() {
        catstorage.flush();
    }
    
    public void afterPropertiesSet() throws Exception {
        catstorage = CatStorageImpl.getInstance(resources);
    }

    /**
     * 获取资源文件
     * @return 资源文件
     * @since 0.7
     */
    public File[] getResources() {
        return resources;
    }

    /**
     * 设定资源文件
     * @param resources 资源文件
     * @since 0.7
     */
    public void setResources(File[] resources) {
        this.resources = resources;
    }
}
