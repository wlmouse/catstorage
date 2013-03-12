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
 *  @(#)FilterFactoryTest.java    0.7 08/21/2011
 */
package org.miao.catstorage.core;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 过滤器工厂单元测试
 * @author Miao
 * @version 0.7
 * @since 0.1
 */
public class FilterFactoryTest {

    public FilterFactoryTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * 获得过滤器方法测试
     * @since 0.1
     */
    @Test
    public void testGetFilter() {
        System.out.println("getFilter");
        Filter result = FilterFactory.getFilter();
        assertNotNull(result);
    }
}
