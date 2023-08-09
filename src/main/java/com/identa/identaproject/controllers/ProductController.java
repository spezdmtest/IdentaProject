package com.identa.identaproject.controllers;

import com.identa.identaproject.dto.ProductDTO;
import com.identa.identaproject.service.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductServiceImpl productServiceImpl;

    public String list(Model model){
        List<ProductDTO> listProducts = productServiceImpl.getAll();
        model.addAttribute("products", listProducts);
        return "products";
    }
}
