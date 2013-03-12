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
 *  @(#)StatementFilterTest.java   0.7 03/20/2011
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

/**
 * 语句过滤器单元测试
 * @author Miao
 * @version 0.7
 * @since 0.1
 */
public class StatementFilterTest {

    public StatementFilterTest() {
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
     * 语句过滤方法测试(空参数)
     * @since 0.1
     */
    @Test
    public void testFilterByNullParam() {
        System.out.println("filterByNullParam");
        String statement = "select c from cat c where #[and c.name = :name]##[and c.age >= :age]##[or c.sex = :sex]#";
        List<String> parameters = new ArrayList<String>();
        StatementFilter instance = new StatementFilter();
        String nullParameterExpResult = "select c from cat c where";
        String nullParameterResult = instance.filter(statement, parameters).trim();
        assertEquals(nullParameterExpResult, nullParameterResult);

    }

    /**
     * 语句过滤方法测试(部分参数)
     * @since 0.7
     */
    @Test
    public void testFilterByParam() {
        System.out.println("filterByParam");
        String statement = "select c from cat c where #[and c.name = :name]##[and c.age >= :age]##[or c.sex = :sex]#";
        List<String> parameters = new ArrayList<String>();
        StatementFilter instance = new StatementFilter();
        parameters.add("name");
        parameters.add("sex");
        String parameterExpResult = "select c from cat c where  and c.name = :name  or c.sex = :sex";
        String pparameterResult = instance.filter(statement, parameters).trim();
        assertEquals(parameterExpResult, pparameterResult);


    }

    /**
     * 语句过滤方法测试(全参数)
     * @since 0.7
     */
    @Test
    public void testFilterByAllParam() {
        System.out.println("filterByAllParam");
        String statement = "select c from cat c where #[and c.name = :name]##[and c.age >= :age]##[or c.sex = :sex]#";
        List<String> parameters = new ArrayList<String>();
        StatementFilter instance = new StatementFilter();
        parameters.add("name");
        parameters.add("age");
        parameters.add("sex");
        String allParameterExpResult = "select c from cat c where  and c.name = :name  and c.age >= :age  or c.sex = :sex";
        String allPparameterResult = instance.filter(statement, parameters).trim();
        assertEquals(allParameterExpResult, allPparameterResult);
    }
}
