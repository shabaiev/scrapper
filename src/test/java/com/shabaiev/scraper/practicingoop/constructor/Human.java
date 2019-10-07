package com.shabaiev.scraper.practicingoop.constructor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Human {
    private String ethnicity;
    private int age;
    private String firstName;
    private String lastName;
    private String middleName;
    private String primaryLanguage;

    public Human(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public Human(String ethnicity, int age){
        this.ethnicity = ethnicity;
        this.age = age;
    }

    //copy constructor
    public Human(Human human){
        this.ethnicity = human.getEthnicity();
        this.age = human.getAge();
        this.firstName = human.getFirstName();
        this.lastName = human.getLastName();
        this.middleName = human.getMiddleName();
        this.primaryLanguage = human.getPrimaryLanguage();
    }


}
