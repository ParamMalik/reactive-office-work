package com.app.reactive.dao.impl;

import com.app.reactive.dao.ProductDao;
import com.app.reactive.dto.ProductDto;
import com.app.reactive.repository.ProductRepository;
import com.app.reactive.utils.ModelDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {

    private final ProductRepository productRepository;

    @Override
    public Mono<ProductDto> getProductById(String id) {
        return productRepository
                .findById(id)
                .map(ModelDtoMapper.INSTANCE::modelToDtoMapping);
    }

    @Override
    public Flux<ProductDto> getAllProduct() {
        return productRepository
                .findAll()
                .map(ModelDtoMapper.INSTANCE::modelToDtoMapping);
    }

    @Override
    public Mono<ProductDto> addProduct(ProductDto productDto) {
        return productRepository
                .save(ModelDtoMapper.INSTANCE.dtoToModelMapping(productDto))
                .map(ModelDtoMapper.INSTANCE::modelToDtoMapping);
    }


    @Override
    public Mono<ProductDto> removeProductById(String id) {
        return productRepository
                .findById(id)
                .map(ModelDtoMapper.INSTANCE::modelToDtoMapping);
    }

    @Override
    public Mono<ProductDto> updateProduct(ProductDto productDto) {
        return productRepository
                .findByName(productDto.getName())
                .defaultIfEmpty(productDto)
                .map(foundProduct -> {
                    foundProduct.setName(productDto.getName());
                    foundProduct.setQuantity(productDto.getQuantity());
                    return foundProduct;
                })
                .map(ModelDtoMapper.INSTANCE::dtoToModelMapping)
                .flatMap(productRepository::save)
                .map(ModelDtoMapper.INSTANCE::modelToDtoMapping);
    }

    @Override
    public Mono<ProductDto> partialUpdateProduct(ProductDto productDto) {

        return productRepository.findByName(productDto.getName());
    }


}
