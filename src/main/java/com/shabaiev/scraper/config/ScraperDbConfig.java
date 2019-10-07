package com.shabaiev.scraper.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
//@ConfigurationProperties(prefix = "scraper-db.datasource")
public class ScraperDbConfig{

    @Value("${scraper-db.datasource.jdbcUrl}")
    private String jdbcUrl;

    @Value("${scraper-db.datasource.username}")
    private String username;

//    @Value("${scraper-db.datasource.password}")
//    private String password;

    @Value("${scraper-db.datasource.driverClassName}")
    private String driverClassName;

    @Value("${scraper-db.datasource.poolName}")
    private String poolName;

    @Value("${scraper-db.datasource.maxPoolSize}")
    private Integer maxPoolSize;

    @Bean(name = "scraperDataSource")
    DataSource getScraperDataSourceConfig() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        //config.setPassword(password);
        config.setDriverClassName(driverClassName);
        config.setPoolName(poolName);
        config.setMaximumPoolSize(maxPoolSize);
        return new HikariDataSource(config);
    }

    @Bean(name = "scraperJdbcTemplate")
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(@Autowired @Qualifier("scraperDataSource") DataSource scraperDataSource){
        return new NamedParameterJdbcTemplate(new JdbcTemplate(scraperDataSource));
    }
}
