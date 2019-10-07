package com.shabaiev.scraper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    private String reviewHeadline;
    private String reviewText;
    private String reviewDate;
}
