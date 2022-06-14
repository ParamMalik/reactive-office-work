package com.app.reactive.utils;

import com.app.reactive.dto.ProductDto;
import com.app.reactive.model.ProductModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ModelDtoMapper {

    ModelDtoMapper INSTANCE = Mappers.getMapper(ModelDtoMapper.class);

    ProductModel dtoToModelMapping(ProductDto productDto);

    ProductDto modelToDtoMapping(ProductModel productModel);

}