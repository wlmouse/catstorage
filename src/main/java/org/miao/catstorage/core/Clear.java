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
 *  @(#)Clear.java    0.1 04/01/2009
 */
package org.miao.catstorage.core;

/**
 * 清理器接口
 * @author Miao
 * @version 0.1
 * @since 0.1
 */
public interface Clear {

    /**
     * 对语句进行清理
     * @param statement 语句
     * @return 清理完成的语句
     * @since 0.1
     */
    public String clear(String statement);
}
