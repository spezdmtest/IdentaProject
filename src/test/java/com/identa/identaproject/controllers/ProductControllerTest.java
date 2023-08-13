package com.identa.identaproject.controllers;

import com.identa.identaproject.dto.ProductDTO;
import com.identa.identaproject.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private List<ProductDTO> productDTOList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        given(productService.getAll()).willReturn(productDTOList);
    }

    @Test
    void getProductAll() throws Exception {
        productDTOList.add(ProductDTO.builder()
                .id(1L)
                .title("Product_Test1")
                .available(3)
                .price(BigDecimal.valueOf(7))
                .build());

        productDTOList.add(ProductDTO.builder()
                .id(2L)
                .title("Product_Test2")
                .available(4)
                .price(BigDecimal.valueOf(8))
                .build());
        given(productService.getAll()).willReturn(productDTOList);

        ResultActions response = mockMvc.perform(get("/products"));
        response.andExpect(status().isOk())
                .andDo(print());
    }
}
