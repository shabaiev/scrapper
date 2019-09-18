package com.shabaiev.scraper.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplexObject {
    private List<AnotherComplexObject> complexObjects;
    private String title;
}