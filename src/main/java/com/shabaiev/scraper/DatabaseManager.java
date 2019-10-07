package com.shabaiev.scraper;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseManager {

    @Autowired
    @Getter
    private NamedParameterJdbcTemplate scraperJdbcTemplate;

}
