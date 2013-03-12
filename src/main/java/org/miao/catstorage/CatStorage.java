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
 *  @(#)CatStorage.java    0.8 01/27/2013
 */
package org.miao.catstorage;

import java.util.List;
import java.util.Map;

/**
 * 仓库猫接口
 * <p>仓库猫的接口，定义了仓库猫最基本的操作方法</p>
 * @author Miao
 * @version 0.8
 * @since 0.1
 */
public interface CatStorage {
    
    /**
     * 获得构造语句
     * <p>获取原始的预定义语句</p>
     * @param key 语句Key
     * @return 构造语句
     * @since 0.8
     */
    public Result get(String key);

    /**
     * 获得构造语句
     * <p>列表parameters记录了所有查询参数。程序会根据这些参数对预定义语句进行检查。
     * 如果语句中的条件在参数中，则这些条件会被保留，不符合的条件会被删除。</p>
     * @param key 语句Key
     * @param parameters 查询参数
     * @return 构造语句
     * @since 0.1
     */
    public Result get(String key, List<String> parameters);

    /**
     * 获得构造语句
     * <p>Map对象parameters以参数名作为key，参数值作为value。程序会根据key和value对预定义语句进行检查。
     * 如果语句中的条件在参数中且value有值（非null），则这些条件会被保留，不符合的条件会被删除。</p>
     * @param key 语句Key
     * @param parameters 查询参数
     * @return 构造语句
     * @since 0.1
     */
    public Result get(String key, Map<String, Object> parameters);
    
    /**
     * 刷新仓库猫
     * <p>刷新仓库猫的预定义语句库</p>
     * @since 0.8
     */
    public void flush();
}
