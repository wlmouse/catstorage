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
 *  @(#)Filter.java   0.1 04/01/2009
 */
package org.miao.catstorage.core;

import java.util.List;

/**
 * 参数过滤器接口
 * <p>根据参数对语句进行处理，删除语句中不存在于参数中的条件</p>
 * @author Miao
 * @version 0.1
 * @since 0.1
 */
public interface Filter {

    /**
     * 根据参数对语句的查询条件进行过滤
     * @param statement 待过滤语句
     * @param parameters 查询参数
     * @return 处理好的语句
     * @since 0.1
     */
    public String filter(String statement, List<String> parameters);
}
