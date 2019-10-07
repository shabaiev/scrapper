package com.shabaiev.scraper;

import com.shabaiev.scraper.model.Category;
import com.shabaiev.scraper.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EbayDataCollectorService implements DataCollectorService {

    @Value("${scraper.ebay.url}")
    private String baseUrl;
    @Autowired
    private Driver driver;

    @Override
    public List<Category> collectExistingCategories() {
        driver.getChromeDriver().get(baseUrl);
        return null;

    }

    @Override
    public List<Review> collectReviews() {
        return null;
    }

}
