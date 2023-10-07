package com.jatin.fakestore.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    private RestTemplateBuilder restTemplateBuilder;
    private RestTemplate restTemplate;
    private String categoryUrl = "https://fakestoreapi.com/products/categories";

    @Override
    public List<String> getAllCategories() {
        restTemplate = new RestTemplateBuilder().build();
        ResponseEntity<String[]> response = restTemplate.getForEntity(categoryUrl, String[].class);
        return Arrays.asList(response.getBody());
    }
}
