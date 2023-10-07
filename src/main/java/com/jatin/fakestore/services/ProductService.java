package com.jatin.fakestore.services;

import com.jatin.fakestore.dtos.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto getProduct(Long id);

    ProductDto addProduct(ProductDto productDto);

    List<ProductDto> getProducts();

    ProductDto deleteProduct(Long id);

    ProductDto updateProduct(Long id, ProductDto productDto);

    List<ProductDto> getProductByCaterogy(String categoryName);

}
