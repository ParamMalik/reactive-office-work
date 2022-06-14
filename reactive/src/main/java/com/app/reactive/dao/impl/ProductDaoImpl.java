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
    public Mono<Void> removeProductById(String id) {
        return productRepository
                .findById(id)
                .flatMap(product -> {
                    productRepository.delete(product);
                    return product;
                })
                .map();

    }

    @Override
    public Mono<Void> removeAllProducts() {
        return productRepository.deleteAll();
    }

    @Override
    public Mono<ProductDto> updateProduct(ProductDto productDto) {
        return productRepository
                .findByName(productDto.getName())
                .map(product -> {
                    product.setPrice(productDto.getPrice());
                    product.setQuantity(productDto.getQuantity());
                    return product;
                })
                .map(ModelDtoMapper.INSTANCE::dtoToModelMapping)
                .flatMap(productRepository::save)
                .map(ModelDtoMapper.INSTANCE::modelToDtoMapping);
    }

    @Override
    public Mono<ProductDto> partialUpdateProduct(ProductDto productDto) {

        return productRepository
                .findById(productDto.getId())
                .map(product -> {
                            ModelDtoMapper.INSTANCE.updateProductFromDto(productDto, product);
                            return product;
                        }
                ).flatMap(productRepository::save)
                .map(ModelDtoMapper.INSTANCE::modelToDtoMapping);
    }

}
