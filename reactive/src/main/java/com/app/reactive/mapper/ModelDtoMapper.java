package com.app.reactive.mapper;

import com.app.reactive.dto.ProductDto;
import com.app.reactive.model.ProductModel;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ModelDtoMapper {

    ProductModel dtoToModelMapping(ProductDto productDto);

    ProductDto modelToDtoMapping(ProductModel productModel);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromDto(ProductDto productDto, @MappingTarget ProductModel productModel);

}
