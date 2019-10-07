package com.shabaiev.scraper.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class SeleniumConfig {

    @Bean
    @Qualifier("chrome")
    WebDriver getChromeDriver() {
        String os = System.getProperty("os.name");
        File chromeDriver;
        if (os.contains("Mac") || os.contains("mac")) {
            chromeDriver = new File("drivers/mac/chromedriver");
        } else {
            chromeDriver = new File("drivers/win/chromedriver.exe");
        }
        if (!chromeDriver.exists()) {
            throw new IllegalArgumentException("Couldn't find chromedriver.exe in " + chromeDriver.getAbsolutePath());
        }
        System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
        return new ChromeDriver();
    }

}
