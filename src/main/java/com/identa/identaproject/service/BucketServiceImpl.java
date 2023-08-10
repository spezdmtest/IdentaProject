package com.identa.identaproject.service;

import com.identa.identaproject.dto.BucketDTO;
import com.identa.identaproject.entities.Bucket;
import com.identa.identaproject.repository.BucketRepository;
import com.identa.identaproject.repository.ProductRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class BucketServiceImpl implements BucketService {

    private final BucketRepository bucketRepository;
    private final ProductRepository productRepository;
    private boolean quantity;

    @Override
    public Bucket createBucket(List<Long> productIds) {
        return null;
    }

    @Override
    public void addProducts(List<Long> productIds) {
    }

    @Override
    public BucketDTO getBucket() {
        return null;
    }

    @Override
    public boolean isNotQuantity() {
        return false;
    }
}
