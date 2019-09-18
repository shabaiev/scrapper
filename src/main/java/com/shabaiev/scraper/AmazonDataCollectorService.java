package com.shabaiev.scraper;

import com.shabaiev.scraper.model.Category;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AmazonDataCollectorService implements DataCollectorService {

    @Value("${scraper.amazon.url}")
    private String baseUrl;
    @Autowired
    private Driver driver;

    @Override
    public List<Category> collectExistingCategories() {
        List<Category> categoryList = new ArrayList<>();
        driver.getChromeDriver().get(baseUrl);
        Document document = driver.getCurrentPage();
        Element openLeftPaneButton = document.getElementsByAttributeValue("aria-label", "Open Menu").first();
        driver.getChromeDriver().findElement(By.cssSelector(openLeftPaneButton.cssSelector())).click();
        boolean leftMenuDisplayed = driver.waitForElement("id", "hmenu-content");
        if (!leftMenuDisplayed){
            throw new IllegalArgumentException("Ooops! Couldn't load left pane menu");
        }
        document = driver.getCurrentPage();
        Element leftPane = document.getElementsByAttributeValue("id", "hmenu-content").first();
        Elements categories = leftPane.getElementsByTag("ul");
        categories.forEach(category -> {
            Elements subCategories = category.getElementsByTag("li");
            subCategories.forEach(subCategory -> {
                Optional<Element> aOptional = Optional.ofNullable(subCategory.getElementsByTag("a").first());
                if (aOptional.isPresent()){
                    Element a = aOptional.get();
                    String subCategoryName = a.text();
                    String subCategoryUrl = a.attr("href");
                    Category category1 = new Category(subCategoryName,subCategoryUrl);
                    categoryList.add(category1);
                }
            });
        });
        List<Category> filteredCategoryList = categoryList.stream()
                .filter(category -> !category.getCategoryUrl().equals(""))
                .filter(category -> !category.getCategoryUrl().equals("/"))
                .collect(Collectors.toList());
        filteredCategoryList.forEach(category -> category.setCategoryUrl(baseUrl + category.getCategoryUrl()));
        return filteredCategoryList;
    }
}
