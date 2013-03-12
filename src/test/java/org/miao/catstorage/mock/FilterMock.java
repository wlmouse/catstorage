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
 *  @(#)FilterMock.java   0.7 08/21/2011
 */
package org.miao.catstorage.mock;

import java.util.List;
import org.miao.catstorage.core.Filter;

/**
 * 过滤器Mock对象
 * <p>用于在单元测试中模拟Filter接口的实例</p>
 * @author Miao
 * @version 0.7
 * @since 0.1
 */
public class FilterMock implements Filter {

    /**
     * 根据参数对语句的查询条件进行过滤
     * <p>测试返回待过滤语句加" success"字符串</p>
     * @param statement 待过滤语句
     * @param parameters 参数
     * @return 处理好的语句
     * @since 0.1
     */
    @Override
    public String filter(String statement, List<String> parameters) {
        return statement + " success";
    }
}
