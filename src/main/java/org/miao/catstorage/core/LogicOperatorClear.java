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
 *  @(#)LogicOperatorClear.java    0.2 01/15/2010
 */
package org.miao.catstorage.core;

import java.util.regex.Pattern;

/**
 * 逻辑运算符清理器
 * <p>该清理器用于清理语句中作废的“and”、“or”关键字</p>
 * @author Miao
 * @version 0.2
 * @since 0.1
 */
final class LogicOperatorClear implements Clear {

    /**
     * 清理废逻辑运算符的正则表达式
     */
    private final Pattern logicOperatorRegex = Pattern.compile("(where|having|\\()\\s+(\\band\\b|\\bor\\b)\\s+", Pattern.CASE_INSENSITIVE);

    public String clear(String statement) {
        return logicOperatorRegex.matcher(statement).replaceAll("$1 ");
    }
}
