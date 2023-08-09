package com.identa.identaproject.mapper;

import com.identa.identaproject.dto.ProductDTO;
import com.identa.identaproject.entities.Product;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class ProductMapper {
    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    abstract Product toProduct(ProductDTO dto);

    @InheritInverseConfiguration
    abstract ProductDTO fromProduct(Product product);

    abstract List<Product> toProductList(List<ProductDTO> productDTOS);
    abstract List<ProductDTO> fromProductList(List<Product> products);
}
