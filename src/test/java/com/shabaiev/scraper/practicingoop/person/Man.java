package com.shabaiev.scraper.practicingoop.person;

public class Man extends Person{


    public Man(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Man() {
    }

    public void speak(){
        System.out.println("I learned how to speak at age of 4");
    }

}
