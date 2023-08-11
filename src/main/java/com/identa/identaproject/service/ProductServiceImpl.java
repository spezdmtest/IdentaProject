package com.identa.identaproject.service;

import com.identa.identaproject.dto.ProductDTO;
import com.identa.identaproject.entities.Bucket;
import com.identa.identaproject.entities.User;
import com.identa.identaproject.mapper.BucketMapper;
import com.identa.identaproject.mapper.ProductMapper;
import com.identa.identaproject.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper mapper = ProductMapper.MAPPER;

    private final ProductRepository repository;
    private final SimpMessagingTemplate template;
    private final UserService userService;
    private final BucketService bucketService;

    @Override
    public List<ProductDTO> getAll() {
        return mapper.fromProductList(repository.findAll());
    }

    @Override
    public void addProduct(ProductDTO productDTO) {
        var product = mapper.toProduct(productDTO);
        var saveProduct = repository.save(product);
        template.convertAndSend("/topic/products", ProductMapper.MAPPER.fromProduct(saveProduct));
    }

    @Override
    public void addToUserBucket(Long productId, String userByEmail) {
        User user = userService.findByName(userByEmail);
        if (user == null ) {
            throw new RuntimeException("User not found - " + userByEmail);
        }
        Bucket bucket = user.getBucket();
        if (bucket == null) {
            Bucket newBucket = bucketService.createBucket(user, Collections.singletonList(productId));
            user.setBucket(newBucket);
            userService.save(user);
        } else {
            bucketService.addProducts(bucket, Collections.singletonList(productId));
            template.convertAndSend("/topic/buckets", BucketMapper.MAPPER.fromBucket(bucket));
        }
    }
}
