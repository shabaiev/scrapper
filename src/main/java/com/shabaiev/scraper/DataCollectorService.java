package com.shabaiev.scraper;

import com.shabaiev.scraper.model.Category;

import com.shabaiev.scraper.model.Review;

import java.util.List;

public interface DataCollectorService {

    public List<Category>  collectExistingCategories();

    public List<Review> collectReviews();


}
