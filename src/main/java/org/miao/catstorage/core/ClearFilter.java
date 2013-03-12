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
 *  @(#)ClearFilter.java   0.2 01/15/2010
 */
package org.miao.catstorage.core;

import java.util.Collections;
import java.util.List;

/**
 * 语句清理过滤器
 * <p>用于清理语句中多余的括号、空白、逻辑操作符、"Where"关键字和"Having"关键字。</p>
 * @author Miao
 * @version 0.2
 * @since 0.1
 */
final class ClearFilter implements Filter {

    /**
     * 底层过滤器
     */
    private Filter filter;
    /**
     * 清理器
     */
    private List<Clear> clears = Collections.emptyList();

    /**
     * 构造方法
     * @param filter 底层过滤器
     * @since 0.1
     */
    public ClearFilter(Filter filter) {
        this.filter = filter;
    }

    /**
     * 构造方法
     * @param filter 底层过滤器
     * @param clears 清理器
     * @since 0.2
     */
    public ClearFilter(Filter filter, List<Clear> clears) {
        this.filter = filter;
        this.clears = clears;
    }

    public String filter(String statement, List<String> parameters) {
        String filterResult = filter.filter(statement, parameters);
        for (Clear clear : clears) {
            filterResult = clear.clear(filterResult);
        }
        return filterResult;
    }

    /**
     * 设置清理器
     * @param clears 清理器
     * @since 0.1
     */
    public void setClears(List<Clear> clears) {
        if (clears != null) {
            this.clears = clears;
        }
    }
}
