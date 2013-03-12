/*
 * Copyright 2013 Miao.
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
 * @(#)CatStorageImplTest.java    0.8 01/28/2013
 */
package org.miao.catstorage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 仓库猫实现的单元测试
 * @author Miao
 * @version 0.8
 * @since 0.8
 */
public class CatStorageImplTest {
    
    /**
     * 测试的Json文件
     */
    private File testJson = null;
    /**
     * 日志
     */
    private static Log log = LogFactory.getLog(CatStorageImplTest.class);
    
    public CatStorageImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        FileWriter writer = null;
        try {
            testJson = File.createTempFile("testread", ".json");
            writer = new FileWriter(testJson);
            writer.write("{\"cat\":\"cat2\",\"mouse\":\"mouse2\"}");
            writer.flush();
        } catch (IOException e) {
            log.error(getClass().getName() + "单元测试初始化错误", e);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                log.error(getClass().getName() + "单元测试初始化错误", ex);
            }
        }
    }
    
    @After
    public void tearDown() {
        if (testJson != null && testJson.exists()) {
            testJson.deleteOnExit();
        }
    }

    /**
     * 获得构造语句方法测试
     * @since 0.8
     */
    @Test
    public void testGet_String() {
        System.out.println("get");
        String key = "cat";
        CatStorage instance = CatStorageImpl.getInstance(testJson);
        Result expResult = new Result("cat2");
        Result result = instance.get(key);
        assertEquals(expResult, result);
    }

    /**
     * 获得构造语句方法测试
     * @since 0.8
     */
    @Test
    public void testGet_String_List() {
        System.out.println("get");
        String key = "cat";
        List<String> parameters = Collections.emptyList();
        CatStorage instance = CatStorageImpl.getInstance(testJson);
        Result expResult = new Result("cat2");
        Result result = instance.get(key, parameters);
        assertEquals(expResult, result);
    }

    /**
     * 获得构造语句方法测试
     * @since 0.8
     */
    @Test
    public void testGet_String_Map() {
        System.out.println("get");
        String key = "cat";
        Map<String, Object> parameters = Collections.emptyMap();
        CatStorage instance = CatStorageImpl.getInstance(testJson);
        Result expResult = new Result("cat2");
        Result result = instance.get(key, parameters);
        assertEquals(expResult, result);
    }

    /**
     * 构造方法单元测试
     * @since 0.8
     */
    @Test
    public void testGetInstance_File() {
        System.out.println("getInstance");
        CatStorage result = CatStorageImpl.getInstance(testJson);
        assertNotNull(result);
    }

    /**
     * 构造方法单元测试
     * @since 0.8
     */
    @Test
    public void testGetInstance_FileArr() {
        System.out.println("getInstance");
        CatStorage result = CatStorageImpl.getInstance(new File[]{testJson});
        assertNotNull(result);
    }

    /**
     * 构造方法单元测试
     * @since 0.8
     */
    @Test
    public void testGetInstance_String() {
        System.out.println("getInstance");
        CatStorage result = CatStorageImpl.getInstance(testJson.getPath());
        assertNotNull(result);
    }

    /**
     * 构造方法单元测试
     * @since 0.8
     */
    @Test
    public void testGetInstance_StringArr() {
        System.out.println("getInstance");
        CatStorage result = CatStorageImpl.getInstance(new String[]{testJson.getPath()});
        assertNotNull(result);
    }

    /**
     * 刷新仓库猫方法测试
     * @since 0.8
     */
    @Test
    public void testFlush() {
        System.out.println("flush");
        CatStorage instance = CatStorageImpl.getInstance(testJson);
        instance.flush();
        assertTrue(true);
    }
}
