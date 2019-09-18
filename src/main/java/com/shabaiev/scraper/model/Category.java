package com.shabaiev.scraper.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
@AllArgsConstructor
public class Category {
    private String categoryName;
    private String categoryUrl;
}
