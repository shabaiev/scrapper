package com.shabaiev.scraper;

import org.junit.Test;

public class SystemTest {

    @Test
    public void test(){
        String os = System.getProperty("os.name");
        String javaVersion = System.getProperty("java.version");
        System.out.println();

    }
}
