package com.identa.identaproject.controllers;

import com.identa.identaproject.dto.BucketDTO;
import com.identa.identaproject.dto.ProductDTO;
import com.identa.identaproject.service.BucketService;
import com.identa.identaproject.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/bucket")
public class BucketController {

    private final BucketService bucketService;
    private final ProductService productService;
    String userNameByEmail = "my@email.com";

    @GetMapping
    public String viewBucket(Model model) {
        BucketDTO bucketByUser = bucketService.getBucketByUser(userNameByEmail);
        model.addAttribute("bucket", bucketByUser);
        return "bucket";
    }

    @PostMapping
    public ResponseEntity<Void> addProductInBucket (ProductDTO productDTO) {
        productService.addToUserBucket(productDTO.getId(),userNameByEmail);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @MessageMapping("/bucket")
    public void messageAddProductInBucket (ProductDTO productDTO) {
        productService.addToUserBucket(productDTO.getId(), userNameByEmail);
    }
}
