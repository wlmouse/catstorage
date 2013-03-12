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
 *  @(#)StatementFilter.java   0.2 01/15/2010
 */
package org.miao.catstorage.core;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 语句过滤器
 * <p>根据参数对待过滤语句进行处理，删除语句中不存在于参数中的条件</p>
 * @author Miao
 * @version 0.7
 * @since 0.1
 */
final class StatementFilter implements Filter {

    /**
     * 预定义参数正则表达式，用于从预定义语句中提取预定义参数
     */
    private final static Pattern predefineParameterRegex = Pattern.compile("(\\:){1}(\\w|\\d)+");
    /**
     * 废弃参数处理的正则表达式开头
     */
    private final static String deprecatedParameterRegexBegin = "#\\[([^(\\]#)])*(";
    /**
     * 废弃参数处理的正则表达式结尾
     */
    private final static String deprecatedParameterRegexEnd = ")([^(\\]#)])*\\]#";
    /**
     * 清除#[]#的正则表达式
     */
    private final static Pattern tagRegex = Pattern.compile("(#\\[)|(\\]#)");

    /**
     * 根据参数对语句的查询条件进行过滤
     * @param statement 待过滤语句
     * @param parameters 查询参数
     * @return 处理好的语句
     * @since 0.1
     */
    public String filter(String statement, List<String> parameters) {
        List<String> deprecatedParameters = getDeprecatedParameters(statement, parameters);
        if (deprecatedParameters.isEmpty()) {
            return tagRegex.matcher(statement).replaceAll(" ");
        } else {
            return parameterFilter(statement, deprecatedParameters);
        }

    }

    /**
     * 获得待过滤语句中的废弃参数
     * @param statement 预定义语句
     * @param parameters 查询参数
     * @return 废弃参数
     * @since 0.1
     */
    private List<String> getDeprecatedParameters(String statement, List<String> parameters) {
        List<String> deprecatedParameters = new ArrayList<String>();
        Matcher matcher = predefineParameterRegex.matcher(statement);
        while (matcher.find()) {
            deprecatedParameters.add(matcher.group().substring(1));
        }
        if (parameters != null) {
            deprecatedParameters.removeAll(parameters);
        }
        return deprecatedParameters;
    }

    /**
     * 生成废弃参数过滤的正则表达式
     * @param deprecatedParameters 废弃参数列表
     * @return 废弃参数过滤正则表达式
     * @since 0.1
     */
    private String createDeprecatedParameters(List<String> deprecatedParameters) {
        StringBuilder regex = new StringBuilder(100);
        regex.append(deprecatedParameterRegexBegin);
        for (String parameter : deprecatedParameters) {
            regex.append("\\:").append(parameter).append("|");
        }
        regex.deleteCharAt(regex.length() - 1);
        regex.append(deprecatedParameterRegexEnd);
        return regex.toString();
    }

    /**
     * 根据废弃参数对语句的查询条件进行过滤
     * @param statement 待过滤语句
     * @param deprecatedParameters 废弃参数
     * @return 处理好的语句
     * @since 0.1
     */
    private String parameterFilter(String statement, List<String> deprecatedParameters) {
        String result = statement.replaceAll(createDeprecatedParameters(deprecatedParameters), "");
        return tagRegex.matcher(result).replaceAll(" ");
    }
}
