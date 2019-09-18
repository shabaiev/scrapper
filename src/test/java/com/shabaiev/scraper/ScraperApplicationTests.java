package com.shabaiev.scraper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScraperApplicationTests {
    @Autowired
    private Driver driver;

	@Test
	public void contextLoads() {
		driver.getChromeDriver().get("https://www.amazon.com/dp/B01J6RPGKG?ref_=nav_em_T1_0_4_9_3__k_ods_tab_sz");
        System.out.println();
	}

}
