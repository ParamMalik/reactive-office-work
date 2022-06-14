package com.app.reactive.controller;

import com.app.reactive.dto.ProductDto;
import com.app.reactive.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/{id}")
    public Mono<ProductDto> getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @GetMapping
    public Flux<ProductDto> getAllProduct() {
        return productService.getAllProduct();
    }

    @PostMapping
    public Mono<ProductDto> addProduct(@RequestBody ProductDto productDto) {
        return productService.addProduct(productDto);
    }

    @DeleteMapping("/{id}")
    public Mono<ProductDto> removeProductById(@PathVariable String id) {
        return productService.removeProductById(id);
    }

    @PutMapping
    public Mono<ProductDto> updateProductByName(@RequestBody ProductDto productDto) {
        return productService.updateProduct(productDto);
    }

    @PatchMapping
    public Mono<ProductDto> partialUpdateProduct(@RequestBody ProductDto productDto) {
        return productService.partialUpdateProduct(productDto);
    }
}
