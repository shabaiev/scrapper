package com.shabaiev.scraper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Driver {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("chrome")
    private WebDriver chromeDriver;

    public WebDriver getChromeDriver() {
        return chromeDriver;
    }

    public Document getJsoupDocument(String pageSource) {
        return Jsoup.parse(pageSource);
    }

    public Document getCurrentPage(){
        String pageSource;
        pageSource = getChromeDriver().getPageSource();
        return getJsoupDocument(pageSource);
    }

    public Document connect(String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (document == null){
            log.error("Could not get Jsoup document from: " + url);
        }
        return document;
    }

    public boolean waitForElement(String attributeKey, String attributeValue) {
        boolean result = false;
        int maxTries = 5;
        //get current page as Document                  V
        //find elements by attribute key and value      V
        //validate that size is > 0
        //if not wait for 1 sec
        //check again 20 times
        Document document;
        Elements els;
        for (int i=0; i<maxTries; i++) {
            document = getCurrentPage();
            els = document.getElementsByAttributeValue(attributeKey, attributeValue);
            if (els.size() ==0){
                //if-nay
                log.info("Element " + attributeKey + "=" + attributeValue + " is not present...");
                sleep(1000);
            }else{
                //else=yay
                log.info("Found element: "  + attributeKey + "=" + attributeValue);
                result = true;
                break;
            }
        }
        return result;
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
