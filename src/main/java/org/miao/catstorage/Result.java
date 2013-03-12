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
 *  @(#)Result.java    0.8 01/25/2013
 */
package org.miao.catstorage;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;

/**
 * 结果对象
 * @author Miao
 * @version 0.8
 * @since 0.1
 */
public final class Result implements Serializable {

    /**
     * 语句
     */
    private final String statement;
    /**
     * 排序列表
     */
    private final LinkedList<String> orders;
    /**
     * 语句结果匹配正则表达式
     */
    private final Pattern regex = Pattern.compile("(SELECT)\\s+([\\w|\\.|\\d]+)\\s+(FROM.*)", Pattern.CASE_INSENSITIVE);
    /**
     * 逗号结尾匹配正则表达式
     */
    private final Pattern commaRegex = Pattern.compile(",\\s*$");
    /**
     * 数量统计正则表达式
     */
    private final String countRegex = "$1 count\\($2\\) $3";
    /**
     * 不同结果正则表达式
     */
    private final String distinctRegex = "$1 distinct $2 $3";
    /**
     * 不同结果统计正则表达式
     */
    private final String countDistinctRegex = "$1 count\\(distinct $2\\) $3";
    /**
     * 求最大值正则表达式
     */
    private final String maxRegex = "$1 max\\($2\\) $3";
    /**
     * 求最小值正则表达式
     */
    private final String minRegex = "$1 min\\($2\\) $3";
    /**
     * 求和正则表达式
     */
    private final String sumRegex = "$1 sum\\($2\\) $3";
    /**
     * 求平均数正则表达式
     */
    private final String avgRegex = "$1 avg\\($2\\) $3";

    /**
     * 构造方法
     * @param statement 语句
     * @throws NullPointerException 当参数statement为null时，抛出NullPointerException异常
     * @since 0.1
     */
    public Result(String statement) {
        if (StringUtils.isNotEmpty(statement)) {
            this.statement = statement;
            orders = new LinkedList<String>();
        } else {
            throw new NullPointerException("param statement is null");
        }
    }

    /**
     * 增加ASC排序
     * @param order 排序属性
     * @throws NullPointerException 当参数order为null或空字符串时，抛出NullPointerException异常
     * @since 0.1
     */
    public void addOrder(String order) {
        if (StringUtils.isEmpty(order)) {
            throw new NullPointerException("param order is null");
        }
        orders.add(order);
    }

    /**
     * 增加DESC排序
     * @param order 排序属性
     * @throws NullPointerException 当参数order为null时，抛出NullPointerException异常
     * @since 0.21
     */
    public void addDescOrder(String order) {
        addOrder(order + " DESC");
    }

    /**
     * 数量统计
     * @return 语句
     * @since 0.1
     */
    public String count() {
        return regex.matcher(statement).replaceAll(countRegex);
    }

    /**
     * 不同结果
     * @return 语句
     * @since 0.1
     */
    public String distinct() {
        return regex.matcher(statement).replaceAll(distinctRegex);
    }

    /**
     * 不同结果统计
     * @return 语句
     * @since 0.1
     */
    public String countDistinct() {
        return regex.matcher(statement).replaceAll(countDistinctRegex);
    }

    /**
     * 求最大值
     * @return 语句
     * @since 0.1
     */
    public String max() {
        return regex.matcher(statement).replaceAll(maxRegex);
    }

    /**
     * 求最小值
     * @return 语句
     * @since 0.1
     */
    public String min() {
        return regex.matcher(statement).replaceAll(minRegex);
    }

    /**
     * 求和
     * @return 语句
     * @since 0.1
     */
    public String sum() {
        return regex.matcher(statement).replaceAll(sumRegex);
    }

    /**
     * 求平均数
     * @return 语句
     * @since 0.1
     */
    public String avg() {
        return regex.matcher(statement).replaceAll(avgRegex);
    }

    @Override
    public String toString() {
        if (orders.isEmpty()) {
            return statement;
        } else {
            StringBuilder sb = new StringBuilder(statement.length() * 2);
            sb.append(statement).append(" ORDER BY ");
            for (String order : orders) {
                sb.append(order).append(", ");
            }
            return commaRegex.matcher(sb).replaceAll("");
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (statement != null ? statement.hashCode() : 0);
        hash += (orders != null ? orders.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Result)) {
            return false;
        }
        Result other = (Result) obj;
        if ((this.statement == null && other.statement != null) || (this.statement != null && !this.statement.equals(other.statement))) {
            return false;
        }
        if ((this.orders == null && other.orders != null) || (this.orders != null && !this.orders.equals(other.orders))) {
            return false;
        }
        return true;
    }

    /**
     * 获得排序列表
     * @return 排序列表
     * @since 0.1
     */
    public List<String> getOrders() {
        return (List<String>) orders.clone();
    }
    
    /**
     * 去除所有排序
     * @since 0.8
     */
    public void clearOrder() {
        orders.clear();
    }
}
