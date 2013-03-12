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
 *  @(#)ResultTest.java    0.8 01/25/2013
 */
package org.miao.catstorage;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 结果对象单元测试
 * @author Miao
 * @version 0.8
 * @since 0.1
 */
public class ResultTest {

    public ResultTest() {
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
     * 构造方法测试
     * @since 0.1
     */
    @Test
    public void testResult() {
        System.out.println("构造方法");
        Result instance = new Result("Select i.name from info i");
        assertNotNull(instance);
        try {
            Result nullInstance = new Result(null);
            fail("使用null创建Result实例未抛出异常");
        } catch (NullPointerException e) {
            assertTrue(true);
        }


    }

    /**
     * 数量统计方法测试
     * @since 0.1
     */
    @Test
    public void testCount() {
        System.out.println("count");
        Result instance = new Result("Select i.name from info i");
        String expResult = "Select count(i.name) from info i";
        String result = instance.count();
        assertEquals(expResult, result);
    }

    /**
     * 不同结果方法测试
     * @since 0.1
     */
    @Test
    public void testDistinct() {
        System.out.println("distinct");
        Result instance = new Result("Select i.name from info i");
        String expResult = "Select distinct i.name from info i";
        String result = instance.distinct();
        assertEquals(expResult, result);
    }

    /**
     * 不同结果统计方法测试
     * @since 0.1
     */
    @Test
    public void testCountDistinct() {
        System.out.println("countDistinct");
        Result instance = new Result("Select i.name from info i");
        String expResult = "Select count(distinct i.name) from info i";
        String result = instance.countDistinct();
        assertEquals(expResult, result);
    }

    /**
     * 求最大值方法测试
     * @since 0.1
     */
    @Test
    public void testMax() {
        System.out.println("max");
        Result instance = new Result("Select i.age from info i");
        String expResult = "Select max(i.age) from info i";
        String result = instance.max();
        assertEquals(expResult, result);
    }

    /**
     * 求最小值方法测试
     * @since 0.1
     */
    @Test
    public void testMin() {
        System.out.println("min");
        Result instance = new Result("Select i.age from info i");
        String expResult = "Select min(i.age) from info i";
        String result = instance.min();
        assertEquals(expResult, result);
    }

    /**
     * 求和方法测试
     * @since 0.3
     */
    @Test
    public void testSum() {
        System.out.println("sum");
        Result instance = new Result("Select i.age from info i");
        String expResult = "Select sum(i.age) from info i";
        String result = instance.sum();
        assertEquals(expResult, result);
    }

    /**
     * 求平均数
     * @since 0.1
     */
    @Test
    public void testAvg() {
        System.out.println("avg");
        Result instance = new Result("Select i.age from info i");
        String expResult = "Select avg(i.age) from info i";
        String result = instance.avg();
        assertEquals(expResult, result);
    }

    /**
     * toString方法测试
     * @since 0.1
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Result instance = new Result("SELECT p FROM Person p");
        String expResult = "SELECT p FROM Person p";
        String result = instance.toString();
        assertEquals(expResult, result);
        instance.addOrder("p.name desc");
        instance.addOrder("p.age");
        expResult += " ORDER BY p.name desc, p.age";
        result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * hashCode方法测试
     * @since 0.1
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Result instance = new Result("Test");
        Result instance2 = new Result("Test");
        assertEquals(instance.hashCode(), instance2.hashCode());
        instance.addOrder("test1");
        assertTrue(instance.hashCode() != instance2.hashCode());
    }

    /**
     * equals方法测试
     * @since 0.3
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new Result("TEST");
        Result instance = new Result("TEST");
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * 增加ASC排序方法测试
     * @since 0.1
     */
    @Test
    public void testAddOrder() {
        System.out.println("addOrder");
        Result instance = new Result("select * from person");
        instance.addOrder("person.name");
        instance.addOrder("person.age");
        List<String> orders = instance.getOrders();
        assertTrue(orders.contains("person.name"));
        assertTrue(orders.contains("person.age"));
        try {
            instance.addOrder(null);
            fail("使用null排序未抛出异常");
        } catch (NullPointerException e) {
            assertTrue(true);
        }
        try {
            instance.addOrder("");
            fail("使用空字符串排序未抛出异常");
        } catch (NullPointerException e) {
            assertTrue(true);
        }
    }

    /**
     * 增加DESC排序测试
     * @since 0.21
     */
    @Test
    public void testAddDescOrder() {
        System.out.println("addDescOrder");
        Result instance = new Result("select * from person");
        instance.addDescOrder("person.name");
        instance.addDescOrder("person.age");
        List<String> orders = instance.getOrders();
        assertTrue(orders.contains("person.name DESC"));
        assertTrue(orders.contains("person.age DESC"));
        try {
            instance.addOrder(null);
            fail("使用null排序未抛出异常");
        } catch (NullPointerException e) {
            assertTrue(true);
        }
        try {
            instance.addOrder("");
            fail("使用空字符串排序未抛出异常");
        } catch (NullPointerException e) {
            assertTrue(true);
        }
    }

    /**
     * 获得排序列表测试
     * @since 0.8
     */
    @Test
    public void testGetOrders() {
        System.out.println("getOrders");
        Result instance = new Result("select * from person");
        List<String> result = instance.getOrders();
        assertNotNull(result);
    }

    /**
     * 去除所有排序测试
     * @since 0.8
     */
    @Test
    public void testClearOrder() {
        System.out.println("clearOrder");
        Result instance = new Result("select * from person");
        assertTrue(instance.getOrders().isEmpty());
        instance.addOrder("person.name");
        assertFalse(instance.getOrders().isEmpty());
        instance.clearOrder();
        assertTrue(instance.getOrders().isEmpty());
    }
}
