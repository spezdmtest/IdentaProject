package com.identa.identaproject.controllers;

import com.identa.identaproject.dto.ProductDTO;
import com.identa.identaproject.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String list(Model model) {
        List<ProductDTO> listProducts = productService.getAll();
        model.addAttribute("products", listProducts);
        return "products";
    }

    @PostMapping
    public ResponseEntity<Void> addProduct(ProductDTO productDTO) {
        productService.addProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @MessageMapping("/products")
    public void messageAddProduct(ProductDTO productDTO) {
        productService.addProduct(productDTO);
    }
}
