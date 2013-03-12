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
 *  @(#)BracketClear.java    0.2 01/15/2010
 */
package org.miao.catstorage.core;

import java.util.regex.Pattern;

/**
 * 括号清理器
 * <p>该清理器用于清理语句中多余的括号及括号间多余的空白</p>
 * @author Miao
 * @version 0.2
 * @since 0.1
 */
final class BracketClear implements Clear {

    /**
     * 清理废括号的正则表达式
     */
    private final Pattern bracketRegex = Pattern.compile("\\(+\\s*\\)+");
    /**
     * 清理左括号间空白的正则表达式
     */
    private final Pattern leftBracketBlankRegex = Pattern.compile("(\\()\\s+");
    /**
     * 清理右括号间空白的正则表达式
     */
    private final Pattern rightBracketBlankRegex = Pattern.compile("\\s+(\\))");

    public String clear(String statement) {
        String step1 = leftBracketBlankRegex.matcher(statement).replaceAll("$1");
        String setp2 = rightBracketBlankRegex.matcher(step1).replaceAll("$1");
        return bracketRegex.matcher(setp2).replaceAll(" ");
    }
}
