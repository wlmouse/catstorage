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
 *  @(#)JsonResourceReaderTest.java    0.8 01/25/2013
 */
package org.miao.catstorage.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
 * JSON资源读取类单元测试
 * @author Miao
 * @version 0.8
 * @since 0.1
 */
public class JsonResourceReaderTest {

    /**
     * 测试的Json文件列表
     */
    private List<File> testJsons = Collections.emptyList();
    /**
     * 日志
     */
    private static Log log = LogFactory.getLog(JsonResourceReaderTest.class);

    public JsonResourceReaderTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        FileWriter writer = null;
        try {
            File testJson = File.createTempFile("testread", ".json");
            writer = new FileWriter(testJson);
            writer.write("{\"cat\":\"cat2\",\"mouse\":\"mouse2\"}");
            writer.flush();
            testJsons = new ArrayList<File>(1);
            testJsons.add(testJson);
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
     * 获取资源Map方法测试
     * @since 0.8
     */
    @Test
    public void testRead() {
        System.out.println("read");
        JsonResourceReader instance = new JsonResourceReader(testJsons);
        Map<String, String> result = instance.read();
        assertEquals(2, result.size());
    }
}
