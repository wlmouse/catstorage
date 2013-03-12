/*
 * Copyright 2011 Miao.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @(#)StorageException.java    0.2 04/02/2009
 */
package org.miao.catstorage.exception;

/**
 * CatStorage运行时异常
 * @author Miao
 * @version 0.2
 * @since 0.2
 */
public class StorageException extends RuntimeException {

    /**
     * 构造一个新的运行时异常。
     * @since 0.2
     */
    public StorageException() {
        super();
    }

    /**
     * 用指定的详细消息构造一个新的运行时异常
     * @param msg 详细消息
     * @since 0.2
     */
    public StorageException(String msg) {
        super(msg);
    }

    /**
     * 用指定的详细消息和原因构造一个新的运行时异常。
     * @param msg 详细消息
     * @param cause 原因
     * @since 0.2
     */
    public StorageException(String msg, Throwable cause) {
        super(msg, cause);
    }

    /**
     * 用指定的原因和详细消息构造一个新的运行时异常
     * @param cause 原因
     * @since 0.2
     */
    public StorageException(Throwable cause) {
        super(cause);
    }
}
