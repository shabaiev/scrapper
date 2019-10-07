    package com.shabaiev.scraper;

    import com.shabaiev.scraper.model.Category;
    import com.shabaiev.scraper.model.Review;
    import org.jsoup.nodes.Document;
    import org.jsoup.nodes.Element;
    import org.jsoup.select.Elements;
    import org.openqa.selenium.By;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.stereotype.Service;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Optional;
    import java.util.stream.Collectors;

    @Service
    public class AmazonDataCollectorService implements DataCollectorService {

        private Logger log = LoggerFactory.getLogger(AmazonDataCollectorService.class);

        @Value("${scraper.amazon.url}")
        private String baseUrl;
        @Autowired
        private Driver driver;


        public List<Category> collectExisitngCategories() {
            List<Category> categoryList = new ArrayList<>();
            driver.getChromeDriver().get(baseUrl);
            Document document = driver.getCurrentPage();
            Element openLeftPaneButton = document.getElementsByAttributeValue("aria-label", "Open-menu").first();
            driver.getChromeDriver().findElement(By.cssSelector(openLeftPaneButton.cssSelector())).click();
            boolean leftMenuDisplayed = driver.waitForElement("id", "hmenu-content");
            if (!leftMenuDisplayed) {
                throw new IllegalArgumentException("OOPS! Couldn't load left pane menu");
            }
            document = driver.getCurrentPage();
            Element leftPane = document.getElementsByAttributeValue("id", "hmenu-content").first();
            Elements categories = leftPane.getElementsByTag("ul");
            categories.forEach(category -> {
                Elements subCategories = category.getElementsByTag("li");
                subCategories.forEach(subCategory -> {
                    Optional<Element> anOptional = Optional.ofNullable(
                            subCategory.getElementsByTag("a").first());
                    if (anOptional.isPresent()) {
                        Element a = anOptional.get();
                        String subCategoryName = a.text();
                        String subCategoryUrl = a.attr("href");
                        Category category1 = new Category(subCategoryName, subCategoryUrl);
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

        @Override
        public List<Category> collectExistingCategories() {
            return null;
        }

        @Override
        public List<Review> collectReviews() {
            List<Review> reviews = new ArrayList<>();
            driver.getChromeDriver().get("https://www.amazon.com/Canon-Mark-Digital-Camera-Body/dp/B072MZCJKN/ref=sr_1_2?keywords=canon+6d&qid=1570467027&sr=8-2");
            Document document = driver.getCurrentPage();
            Element seeAllReviewsLink = document.getElementsByAttributeValue("review-body", "see-all-reviews-link-foot").first();
            String pageTitle = driver.getChromeDriver().getTitle();
            {
                driver.scrollIntoView(seeAllReviewsLink);
                driver.getChromeDriver().findElement(By.cssSelector(seeAllReviewsLink.cssSelector())).click();
                for (int i = 0; i < 5; i++) {
                    String currentPageTitle = driver.getChromeDriver().getTitle();
                    if (pageTitle.equalsIgnoreCase(currentPageTitle)) {
                        driver.sleep(3);
                    } else {
                        break;
                    }
                }
                while (true) {
                    driver.sleep(2);
                    boolean isReviewDisplayed = driver.waitForElement("data-hook", "review");
                    if (!isReviewDisplayed) {
                        log.info("could not locate reviews on the page");
                    }
                }
            }
        }
    }

