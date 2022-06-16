package com.app.reactive.dao.impl;

import com.app.reactive.dao.ProductDao;
import com.app.reactive.dto.ProductDto;
import com.app.reactive.mapper.ModelDtoMapper;
import com.app.reactive.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {

    private final ProductRepository productRepository;
    private final ModelDtoMapper mapper;

    @Override
    public Mono<ProductDto> getProductById(String id) {
        return productRepository
                .findById(id)
                .map(mapper::modelToDtoMapping);
    }

    @Override
    public Flux<ProductDto> getAllProduct() {
        return productRepository
                .findAll()
                .map(mapper::modelToDtoMapping);
    }

    @Override
    public Mono<ProductDto> addProduct(ProductDto productDto) {
        return productRepository
                .save(mapper.dtoToModelMapping(productDto))
                .map(mapper::modelToDtoMapping);
    }


    @Override
    public Mono<ProductDto> removeProductById(String id) {
        return productRepository
                .findById(id)
                .flatMap(product -> productRepository
                        .delete(product)
                        .thenReturn(mapper.modelToDtoMapping(product)));

    }

    @Override
    public Mono<Void> removeAllProducts() {
        return productRepository.deleteAll();
    }

    @Override
    public Mono<ProductDto> updateProduct(ProductDto productDto) {
        return productRepository
                .findById(productDto.getId())
                .map(product -> {
                    product.setPrice(productDto.getPrice());
                    product.setQuantity(productDto.getQuantity());
                    return product;
                })
                .flatMap(productRepository::save)
                .map(mapper::modelToDtoMapping);
    }

    @Override
    public Mono<ProductDto> partialUpdateProduct(ProductDto productDto) {

        return productRepository
                .findById(productDto.getId())
                .map(product -> {
                            mapper.updateProductFromDto(productDto, product);
                            return product;
                        }
                ).flatMap(productRepository::save)
                .map(mapper::modelToDtoMapping);
    }

}
