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
    private Integer total;
    private BigDecimal price;

    public BucketDetailDTO(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.total = product.getAvailable();
        this.price = product.getPrice();
    }
}
