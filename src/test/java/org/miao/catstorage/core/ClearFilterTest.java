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
 *  @(#)ClearFilterTest.java    0.7 08/21/2011
 */
package org.miao.catstorage.core;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.miao.catstorage.mock.ClearMock;
import org.miao.catstorage.mock.FilterMock;

/**
 * 语句清理过滤器单元测试
 * @author Miao
 * @version 0.7
 * @since 0.1
 */
public class ClearFilterTest {

    public ClearFilterTest() {
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
     * 语句过滤方法测试
     * @since 0.1
     */
    @Test
    public void testFilter() {
        System.out.println("filter");
        String statement = "select";
        List<Clear> clears = new ArrayList<Clear>();
        clears.add(new ClearMock());
        clears.add(new ClearMock());
        ClearFilter instance = new ClearFilter(new FilterMock());
        instance.setClears(clears);
        String expResult = "select success clear clear";
        String result = instance.filter(statement, null);
        assertEquals(expResult, result);
    }
}
