package com.identa.identaproject.service;

import com.identa.identaproject.dto.BucketDTO;
import com.identa.identaproject.entities.Bucket;
import com.identa.identaproject.entities.Product;
import com.identa.identaproject.mapper.ProductMapper;
import com.identa.identaproject.repository.BucketRepository;
import com.identa.identaproject.repository.ProductRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class BucketServiceImpl implements BucketService {

    private final ProductMapper mapper = ProductMapper.MAPPER;

    private final BucketRepository bucketRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;
    private boolean quantity;

    @Override
    @Transactional
    public Bucket createBucket(List<Long> productIds) {
        Bucket bucket = new Bucket();
        List<Product> productList = getCollectProductsByIds(productIds);
        bucket.setProducts(productList);
        return bucketRepository.save(bucket);
    }

    @Override
    public void addProducts(Bucket bucket, List<Long> productIds) {
        List<Product> products = bucket.getProducts();
        List<Product> newProductList = products == null ? new ArrayList<>() : new ArrayList<>(products);
        newProductList.addAll(getCollectProductsByIds(productIds));
        bucket.setProducts(newProductList);
        bucketRepository.save(bucket);
    }

    @Override
    public BucketDTO getBucket() {
        return null;
    }

    @Override
    public boolean isNotQuantity() {
        return false;
    }

    private List<Product> getCollectProductsByIds(List<Long> productIds) {
        return productIds.stream()
                .map(productRepository::getById)
                .collect(Collectors.toList());
    }


}
