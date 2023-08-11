package com.identa.identaproject.service;

import com.identa.identaproject.dto.BucketDTO;
import com.identa.identaproject.dto.BucketDetailDTO;
import com.identa.identaproject.dto.ProductDTO;
import com.identa.identaproject.entities.Bucket;
import com.identa.identaproject.entities.Product;
import com.identa.identaproject.mapper.ProductMapper;
import com.identa.identaproject.repository.BucketRepository;
import com.identa.identaproject.repository.ProductRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Data
@RequiredArgsConstructor
public class BucketServiceImpl implements BucketService {

    private final ProductMapper mapper = ProductMapper.MAPPER;

    private final BucketRepository bucketRepository;
    private final ProductRepository productRepository;
    private boolean quantity;

    @Override
    @Transactional
    public Bucket createBucket(List<Long> productIds) {
        Bucket bucket = new Bucket();
        List<Product> productList = getCollectProductsByIds(productIds);
        bucket.setProducts(productList);
        mapper.fromProductList(productList);
        return bucketRepository.save(bucket);
    }

    @Override
    public void addProducts(Bucket bucket, List<Long> productIds) {
        List<Product> products = bucket.getProducts();
        List<Product> newProductList = products == null ? new ArrayList<>() : new ArrayList<>(products);
        newProductList.addAll(getCollectProductsByIds(productIds));
        bucket.setProducts(newProductList);
        mapper.fromProductList(newProductList);
        bucketRepository.save(bucket);
    }

    @Override
    public BucketDTO getBucket() {
        BucketDTO bucketDTO = new BucketDTO();
        Map<Long, BucketDetailDTO> mapByProductId = new HashMap<>();
        var all = bucketRepository.findAll();
        var list = all.stream().map(Bucket::getProducts).collect(Collectors.toList());
        var products = list.stream().flatMap(List::stream).collect(Collectors.toList());
        countProducts(products, mapByProductId);
        bucketDTO.setDetails(new ArrayList<>(mapByProductId.values()));
        return null;
    }

    private void countProducts(List<Product> products, Map<Long, BucketDetailDTO> mapByProductId) {
        for (Product product : products) {
            BucketDetailDTO bucketDetailDTO = mapByProductId.get(product.getId());
            if (bucketDetailDTO == null) {
                mapByProductId.put(product.getId(), new BucketDetailDTO(product));
            } else {
                bucketDetailDTO.setQuantity(bucketDetailDTO.getQuantity() + 1);
                bucketDetailDTO.setSum(bucketDetailDTO.getSum().add(product.getPrice()));
                if (bucketDetailDTO.getQuantity().compareTo(bucketDetailDTO.getTotal()) > 0) {
                    setQuantity(true);
                }
            }
        }
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
