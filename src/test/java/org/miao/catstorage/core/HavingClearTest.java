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
 *  @(#)HavingClearTest.java    0.1 08/21/2009
 */
package org.miao.catstorage.core;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Having清理器单元测试
 * @author Miao
 * @version 0.1
 * @since 0.1
 */
public class HavingClearTest {

    public HavingClearTest() {
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
     * 语句清理方法测试
     * @since 0.1
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        String statement = "select * from cat group by cat.ave having   \t \n       ";
        HavingClear instance = new HavingClear();
        String expResult = "select * from cat group by cat.ave  ";
        String result = instance.clear(statement);
        assertEquals(expResult, result);
        statement += " ORDER BY cat.avg";
        expResult += "ORDER BY cat.avg";
        result = instance.clear(statement);
        assertEquals(expResult, result);
    }
}
