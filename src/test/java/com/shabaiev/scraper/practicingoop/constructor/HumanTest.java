package com.shabaiev.scraper.practicingoop.constructor;

import org.junit.Test;

public class HumanTest{

    @Test
    public void test(){
        Human human1 = new Human("caucasian");
        Human human2 = new Human("african", 25);
        Human human3 = new Human();
        System.out.println();
        human3.setAge(10);
        human3.setEthnicity("black");
        human3.setFirstName("Bill");
        human3.setPrimaryLanguage("english");
        human3.setMiddleName("William");
        human3.setLastName("Howard");
        System.out.println("Human's age: " + human3.getAge());

        //all args constructor
        Human human4 = new Human("caucasian", 25, "Greg", "Povorozniuk", null, "russian");

        Human human5 = new Human(human4);
        System.out.println();

    }
}
