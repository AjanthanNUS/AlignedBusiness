package com.ajan.demo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class FileManagerTest {


    @Before
    public void init() {
    }

    @Test
    public void testReadFileByLines() {
        List<String> stringList = FileManager.readFileByLines("dolphin.txt");
        Assert.assertEquals("Expecting size of two.", 2, stringList.size());
    }


    @Test
    public void testLoadProperties() {
        Properties props  = FileManager.loadProperties();

        Assert.assertEquals("Expecting four entries", 4, props.size());

    }


}
