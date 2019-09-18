package com.shabaiev.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;

public class TestConnect {

    @Test
    public void start() throws IOException {
        Document doc = Jsoup.connect("https://www.amazon.com/dp/B01J6RPGKG?ref_=nav_em_T1_0_4_9_3__k_ods_tab_sz").get();
        System.out.println();
    }
}

