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
 * @(#)ResourceReaderFactoryTest.java    0.8 01/25/2013
 */
package org.miao.catstorage.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 资源读取工厂单元测试
 * @author Miao
 * @version 0.8
 * @since 0.8
 */
public class ResourceReaderFactoryTest {
    
    /**
     * 测试的Json文件数组
     */
    private File[] testJsons = {};
    /**
     * 日志
     */
    private static Log log = LogFactory.getLog(ResourceReaderFactoryTest.class);
    
    public ResourceReaderFactoryTest() {
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
            File testJson = File.createTempFile("testread", ".json");
            writer = new FileWriter(testJson);
            writer.write("{\"cat\":\"cat2\",\"mouse\":\"mouse2\"}");
            writer.flush();
            testJsons = new File[]{testJson};
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
        for(File testJson : testJsons) {
            if (testJson.exists()) {
                testJson.deleteOnExit();
            }
        }
    }

    /**
     * 构建ResourceReader测试
     * @since 0.8
     */
    @Test
    public void testBuildFileArr() {
        System.out.println("buildByFileArray");
        ResourceReader result = ResourceReaderFactory.build(testJsons);
        assertNotNull(result);
    }

    /**
     * 构建ResourceReader测试
     * @since 0.8
     */
    @Test
    public void testBulidFile() {
        System.out.println("buildByFile");
        ResourceReader result = ResourceReaderFactory.bulid(testJsons[0]);
        assertNotNull(result);
    }

    /**
     * 构建ResourceReader测试
     * @since 0.8
     */
    @Test
    public void testBuildStringArr() {
        System.out.println("buildByStringArray");
        String[] resources = {testJsons[0].getPath()};
        ResourceReader result = ResourceReaderFactory.build(resources);
        assertNotNull(result);
    }

    /**
     * 构建ResourceReader测试
     * @since 0.8
     */
    @Test
    public void testBuildString() {
        System.out.println("buildByString");
        String resource = testJsons[0].getPath();
        ResourceReader result = ResourceReaderFactory.build(resource);
        assertNotNull(result);
    }
}
