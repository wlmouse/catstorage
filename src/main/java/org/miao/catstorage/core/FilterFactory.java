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
 *  @(#)FilterCacheProxy.java    0.7 08/21/2011
 */
package org.miao.catstorage.core;

import java.util.LinkedList;
import java.util.List;

/**
 * 过滤器工厂
 * @author Miao
 * @version 0.7
 * @since 0.1
 */
public final class FilterFactory {

    /**
     * 获得过滤器
     * @return 过滤器
     * @since 0.1
     */
    public static Filter getFilter() {
        return addClear(new StatementFilter());
    }

    /**
     * 为过滤器附加清理器
     * @param filter 过滤器
     * @return 附加了清理器的过滤器
     * @since 0.1
     */
    private static Filter addClear(Filter filter) {
        List<Clear> clears = new LinkedList<Clear>();
        clears.add(new BracketClear());
        clears.add(new LogicOperatorClear());
        clears.add(new HavingClear());
        clears.add(new WhereClear());
        clears.add(new BlankClear());
        return new ClearFilter(filter, clears);
    }
}
