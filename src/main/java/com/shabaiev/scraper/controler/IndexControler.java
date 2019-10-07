package com.shabaiev.scraper.controler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shabaiev.scraper.DataCollectorService;
import com.shabaiev.scraper.DatabaseManager;
import com.shabaiev.scraper.Driver;
import com.shabaiev.scraper.model.Review;
import com.shabaiev.scraper.model.db.Category;
import com.shabaiev.scraper.model.db.Merchant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class IndexControler {
    @Autowired
    private DataCollectorService amazonDataCollectorService;
    @Autowired
    private DataCollectorService ebayDataCollectorService;
    @Autowired
    private DatabaseManager databaseManager;
    @Autowired
    private Driver driver;
    private final Logger log = LoggerFactory.getLogger(IndexControler.class);

    @RequestMapping(method = RequestMethod.GET, path = "/categories")
    public ResponseEntity getCattegories(@RequestBody Merchant merchant) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String sql = String.format("select * from merchant where merchant_id=%d", merchant.getMerchantId());
        List<Map<String, Object>> result = databaseManager.getScraperJdbcTemplate().queryForList(
                sql, new HashMap<>());
        System.out.println();
        String jsonResult = objectMapper.writeValueAsString(result);
        return ResponseEntity.ok(jsonResult);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/category")
    public ResponseEntity addNewCategory(@RequestBody Category category) {
        ObjectMapper objectMapper = new ObjectMapper();
        //insert into table
        databaseManager.getScraperJdbcTemplate().update(String.format("INSERT INTO category values(%d, '%s', '%s', %d)",
                category.getCategoryId(),
                category.getCategoryName(),
                category.getCategoryUrl(),
                category.getMerchantId()
        ), new HashMap<>());
        //TODO verify that record was inserted

        //Convert Category object to json
        String json = "";
        try {
            json = objectMapper.writeValueAsString(category);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(json);
    }

    //return List of Merchant localhost:7878/merchants   GET
    @RequestMapping(method = RequestMethod.GET, path = "/merchants")
    public ResponseEntity returnListOfMerchants() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String sql = "select * from merchant";
        List<Map<String, Object>> result = databaseManager.getScraperJdbcTemplate().queryForList(
                sql, new HashMap<>());
        String jsonResult = objectMapper.writeValueAsString(result);
        return ResponseEntity.ok(jsonResult);
    }
    //return Integer count of categories for merchant  localhost:7878/category/{merchantId}/count GET

    @RequestMapping(method = RequestMethod.GET, path = "/category/{merchantId}/count")
    public ResponseEntity countOfCategories(@PathVariable Integer merchantId) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String sql = String.format("select count(*) as count from category where merchant_id=%d", merchantId);
        Map<String, Object> result = databaseManager.getScraperJdbcTemplate().queryForMap(
                sql, new HashMap<>());
        Long count = (Long) result.get("count");
        log.info("Count: " + count);
        return ResponseEntity.ok(count);
    }

    //return total number of products for merchant  like: localhost:7878/merchants/categoriesCount GET
    @RequestMapping(method = RequestMethod.GET, path = "/products")
    public ResponseEntity numberOfProducts() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String sql = "select m.merchant_name, count(p.product_id) as number_of_products\n" +
                "from merchant m\n" +
                "left outer join product p\n" +
                "on p.merchant_id=m.merchant_id\n" +
                "group by m.merchant_name";
        List<Map<String, Object>> result = databaseManager.getScraperJdbcTemplate().queryForList(sql, new HashMap<>());

        String jsonResult = objectMapper.writeValueAsString(result);
//            Long count = (Long) result.get("count");
//        log.info("Count: " + count);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Control-Allow-Origin", "http://localhost:3000");
        ResponseEntity responseEntity = new ResponseEntity(jsonResult, responseHeaders, HttpStatus.OK);
        return responseEntity;
    }


    @RequestMapping(method = RequestMethod.GET, path = "/collectReview")
    public ResponseEntity collectReview() {
        List<Review> collectedReviews = amazonDataCollectorService.collectReviews();
        return null;
    }

}





//{
//	"Amazon":25,
//	"Ebay":43,
//  "Nike":0
//}


//    @RequestMapping(method = RequestMethod.GET, path = "/complexObject")
//    public ResponseEntity complexObject() {
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        ComplexObject complexObject = new ComplexObject();
//
//        AnotherComplexObject aco1 = new AnotherComplexObject();
//        aco1.setNumbers(Arrays.asList(1.0, 12763.1, 12312.12, 54353.4, 754576.3));
//        aco1.setWords(Arrays.asList("Apple", "Orange", "Banana"));
//        aco1.setNumberOfNumbers(aco1.getNumbers().size());
//        aco1.setNumberOfWords(aco1.getWords().size());
//
//        AnotherComplexObject aco2 = new AnotherComplexObject();
//        aco2.setNumbers(Arrays.asList(7.0, 1.2));
//        aco2.setWords(Arrays.asList("Monkey", "Elephant"));
//        aco2.setNumberOfNumbers(aco1.getNumbers().size());
//        aco2.setNumberOfWords(aco1.getWords().size());
//
//        List<AnotherComplexObject> anotherComplexObjectList = new ArrayList<>();
//        anotherComplexObjectList.add(aco1);
//        anotherComplexObjectList.add(aco2);
//
//        complexObject.setComplexObjects(anotherComplexObjectList);
//        complexObject.setTitle("My stupid object");
//
//        String json = "";
//        try {
//            json = objectMapper.writeValueAsString(complexObject);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return ResponseEntity.ok(json);
//    }
//
//    @RequestMapping(method = RequestMethod.POST, path = "/complexObject")
//    public ResponseEntity addComplexObject(@RequestBody ComplexObject complexObject) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        complexObject.setTitle("haha" + complexObject.getTitle());
//        String json = "";
//        try {
//            json = objectMapper.writeValueAsString(complexObject);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return ResponseEntity.ok(json);
//    }

