package com.shabaiev.scraper.model.db;

import lombok.Data;

@Data
public class Review {

    private int productId;
    private String headline;
    private String reviewText;
}
