package com.jatin.fakestore.controllers;

import com.jatin.fakestore.dtos.ProductDto;
import com.jatin.fakestore.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable ("id") Long id){
        return new ResponseEntity<ProductDto>(
                productService.getProduct(id),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto){
        return new ResponseEntity<ProductDto>(
                productService.addProduct(productDto),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable ("id") Long id){
        return new ResponseEntity<ProductDto>(
                productService.deleteProduct(id),
                HttpStatus.NO_CONTENT
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable ("id") Long id, @RequestBody ProductDto productDto){
        return new ResponseEntity<ProductDto>(
                productService.updateProduct(id, productDto),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts(){
        return new ResponseEntity<List<ProductDto>>(
                productService.getProducts(),
                HttpStatus.OK
        );
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<ProductDto>> getProductByCategory(@PathVariable("categoryName") String categoryName) {
        return new ResponseEntity<List<ProductDto>>(
                productService.getProductByCaterogy(categoryName),
                HttpStatus.OK
        );
    }

}
