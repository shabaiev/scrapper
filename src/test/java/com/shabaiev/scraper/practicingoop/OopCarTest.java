package com.shabaiev.scraper.practicingoop;


import com.shabaiev.scraper.practicingoop.car.Camry;
import com.shabaiev.scraper.practicingoop.car.Car;
import com.shabaiev.scraper.practicingoop.car.Corolla;
import org.junit.Test;

public class OopCarTest {

    @Test
    public void testCar(){
        Car car1 = new Camry();
        Car car2 = new Corolla();
        car1.run();
        car2.run();

    }


}
