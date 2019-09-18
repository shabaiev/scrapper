package com.shabaiev.scraper.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnotherComplexObject {
    private List<Double> numbers;
    private List<String> words;
    private int numberOfNumbers;
    private int numberOfWords;
}
