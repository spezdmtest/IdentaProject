package com.identa.identaproject.service;

import com.identa.identaproject.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAll();
    void addProduct(ProductDTO productDTO);
    void addToUserBucket (Long productId, String userByEmail);

}
