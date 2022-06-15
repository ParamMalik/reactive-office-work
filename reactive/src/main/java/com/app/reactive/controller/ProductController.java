package com.app.reactive.controller;

import com.app.reactive.dto.ProductDto;
import com.app.reactive.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/{id}")
    public Mono<ResponseEntity<ProductDto>> getProductById(@PathVariable String id) {
        return productService.getProductById(id)
                .map(ResponseEntity.ok()::body)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @GetMapping
    public Flux<ProductDto> getAllProduct() {
        return productService.getAllProduct();
    }

    @PostMapping
    public Mono<ResponseEntity<ProductDto>> addProduct(@RequestBody ProductDto productDto) {
        return productService.addProduct(productDto)
                .map(ResponseEntity.accepted()::body)
                .switchIfEmpty(Mono.just(ResponseEntity.noContent().build()));
    }


    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<ProductDto>> removeProductById(@PathVariable String id) {
        return productService.removeProductById(id)
                .map(ResponseEntity.ok()::body)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }

    @DeleteMapping
    public Mono<Void> removeAllProducts() {
        return productService.removeAllProducts();
    }

    // Updating NAME and Price

    @PutMapping
    public Mono<ResponseEntity<ProductDto>> updateProductByName(@RequestBody ProductDto productDto) {
        return productService.updateProduct(productDto)
                .map(ResponseEntity.ok()::body)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    // Partial Update
    @PatchMapping
    public Mono<ResponseEntity<ProductDto>> partialUpdateProduct(@RequestBody ProductDto productDto) {
        return productService.partialUpdateProduct(productDto)
                .map(ResponseEntity.ok()::body)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }
}
