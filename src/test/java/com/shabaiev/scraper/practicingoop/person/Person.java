package com.shabaiev.scraper.practicingoop.person;

public abstract class Person {

    private String firstName;
    private String lastName;

    Person(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person() {
    }

    public void walk(){
        System.out.println("I speak like a person");
    }
    public void speak(){
        System.out.println("I can't speeak unless I learned how to");
    }
    public void work(){
        System.out.println("I can't work unless I learned how to");
    }
}
