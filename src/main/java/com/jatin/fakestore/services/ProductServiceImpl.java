package com.jatin.fakestore.services;

import com.jatin.fakestore.dtos.FakeStoreProductDto;
import com.jatin.fakestore.dtos.ProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private RestTemplateBuilder restTemplateBuilder;
    private RestTemplate restTemplate;
    private String productUrlId = "https://fakestoreapi.com/products/{id}";
    private String ProductUrl = "https://fakestoreapi.com/products";
    private String categoryUrl = "https://fakestoreapi.com/products/category/{categoryName}";
    public ProductServiceImpl(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }

    private ProductDto convertToProductDto(FakeStoreProductDto fakeStoreProductDto){
        ProductDto productDto = new ProductDto();
        productDto.setId(fakeStoreProductDto.getId());
        productDto.setTitle(fakeStoreProductDto.getTitle());
        productDto.setDescription(fakeStoreProductDto.getDescription());
        productDto.setCategory(fakeStoreProductDto.getCategory());
        productDto.setPrice(fakeStoreProductDto.getPrice());
        productDto.setImage(fakeStoreProductDto.getImage());
        return productDto;
    }

    // (url, response type, params in url)
    @Override
    public ProductDto getProduct(Long id) {
        restTemplate = new RestTemplateBuilder().build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(productUrlId, FakeStoreProductDto.class, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return convertToProductDto(fakeStoreProductDto);
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        restTemplate = new RestTemplateBuilder().build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(ProductUrl, productDto, FakeStoreProductDto.class);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return convertToProductDto(fakeStoreProductDto);
    }

    @Override
    public List<ProductDto> getProducts() {
        restTemplate = new RestTemplateBuilder().build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(ProductUrl, FakeStoreProductDto[].class);
        List<ProductDto> productDtos = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : response.getBody()){
            productDtos.add(convertToProductDto(fakeStoreProductDto));
        }
        return productDtos;
    }

    @Override
    public ProductDto deleteProduct(Long id) {
        restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(productUrlId, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return convertToProductDto(fakeStoreProductDto);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(productDto, FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(productUrlId, HttpMethod.PUT, requestCallback, responseExtractor, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return convertToProductDto(fakeStoreProductDto);
    }

    @Override
    public List<ProductDto> getProductByCaterogy(String categoryName) {
        restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(categoryUrl, FakeStoreProductDto[].class, categoryName);
        List<ProductDto> productDtos = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : response.getBody()) {
            productDtos.add(convertToProductDto(fakeStoreProductDto));
        }
        return productDtos;
    }
}
