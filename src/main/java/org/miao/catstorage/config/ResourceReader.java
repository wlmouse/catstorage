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
 * @(#)ResourceReader.java    0.7 08/23/2011
 */
package org.miao.catstorage.config;

import java.util.Map;

/**
 * 资源获取接口
 * @author Miao
 * @version 0.7
 * @since 0.7
 */
public interface ResourceReader {
    
    /**
     * 获取资源Map
     * @return 资源
     * @since 0.7
     */
    public Map<String, String> read();
}
