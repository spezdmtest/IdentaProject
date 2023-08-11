package com.identa.identaproject.service;

import com.identa.identaproject.dto.BucketDTO;
import com.identa.identaproject.dto.BucketDetailDTO;
import com.identa.identaproject.entities.Bucket;
import com.identa.identaproject.entities.Product;
import com.identa.identaproject.entities.User;
import com.identa.identaproject.mapper.ProductMapper;
import com.identa.identaproject.repository.BucketRepository;
import com.identa.identaproject.repository.ProductRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class BucketServiceImpl implements BucketService {

    private final ProductMapper mapper = ProductMapper.MAPPER;

    private final BucketRepository bucketRepository;
    private final ProductRepository productRepository;
    private final UserService userService;
    private boolean quantity;

    @Override
    @Transactional
    public Bucket createBucket(User user, List<Long> productIds) {
        Bucket bucket = new Bucket();
        bucket.setUser(user);
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
    public BucketDTO getBucketByUser(String userNameByEmail) {
        User user = userService.findByName(userNameByEmail);
        if (user == null || user.getBucket() == null) {
            return new BucketDTO();
        }
        BucketDTO bucketDTO = new BucketDTO();
        Map<Long, BucketDetailDTO> mapByProductId = new HashMap<>();
        List<Product> products = user.getBucket().getProducts();
        countProducts(products, mapByProductId);
        bucketDTO.setDetails(new ArrayList<>(mapByProductId.values()));
        bucketDTO.calc();
        return bucketDTO;
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
