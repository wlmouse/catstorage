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
 *  @(#)BlankClear.java    0.2 01/15/2010
 */
package org.miao.catstorage.core;

import java.util.regex.Pattern;

/**
 * 空白清理器
 * <p>该清理器用于清理语句中超过1个以上的空白</p>
 * @author Miao
 * @version 0.2
 * @since 0.1
 */
final class BlankClear implements Clear {

    /**
     * 清理空白的正则表达式
     */
    private final Pattern blankRegex = Pattern.compile("\\s{2,}");

    public String clear(String statement) {
        return blankRegex.matcher(statement).replaceAll(" ");
    }
}
