package com.shabaiev.scraper.practicingoop;

import com.shabaiev.scraper.practicingoop.person.Man;

import org.junit.Test;

public class OopPersonTest {

    @Test
    public void test(){

        Man man1 = new Man();
        man1.speak();
        man1.walk();
        man1.work();

    }
}
