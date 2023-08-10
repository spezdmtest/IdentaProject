package com.identa.identaproject.service;

import com.identa.identaproject.dto.BucketDTO;
import com.identa.identaproject.entities.Bucket;

import java.util.List;

public interface BucketService {
    Bucket createBucket(List<Long> productIds);

    void addProducts(List<Long> productIds);

    BucketDTO getBucket();

    boolean isNotQuantity();
}
