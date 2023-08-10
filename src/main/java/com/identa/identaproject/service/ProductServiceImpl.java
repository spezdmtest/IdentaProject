package com.identa.identaproject.service;

import com.identa.identaproject.dto.ProductDTO;
import com.identa.identaproject.entities.Product;
import com.identa.identaproject.mapper.ProductMapper;
import com.identa.identaproject.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper mapper = ProductMapper.MAPPER;

    private final ProductRepository repository;
    @Override
    public List<ProductDTO> getAll() {
        return mapper.fromProductList(repository.findAll());
    }

    @Override
    public void addProduct(ProductDTO productDTO) {
        Product product = mapper.toProduct(productDTO);
        Product saveProduct = repository.save(product);
    }
}
