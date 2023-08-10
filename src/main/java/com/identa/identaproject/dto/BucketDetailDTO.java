package com.identa.identaproject.dto;

import com.identa.identaproject.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BucketDetailDTO {
    private Long id;
    private String title;
    private Integer quantity;
    private Integer total;
    private BigDecimal sum;

    public BucketDetailDTO(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.quantity = new Integer("1.0");
        this.total = product.getAvailable();
        this.sum = product.getPrice();
    }

}
