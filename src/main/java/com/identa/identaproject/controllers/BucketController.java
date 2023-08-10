package com.identa.identaproject.controllers;

import com.identa.identaproject.dto.BucketDTO;
import com.identa.identaproject.service.BucketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/bucket")
public class BucketController {

    private final BucketService service;

    public BucketDTO viewBucket() {
        BucketDTO bucketDTO = service.getBucket();
        return bucketDTO;
    }
}
