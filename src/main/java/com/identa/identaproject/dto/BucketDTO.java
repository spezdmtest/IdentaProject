package com.identa.identaproject.dto;

import com.identa.identaproject.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BucketDTO {
    private Double sum;
    public List<BucketDetailDTO> details = new ArrayList<>();

    public void calc() {
        this.sum = details.stream()
                .map(BucketDetailDTO::getPrice)
                .mapToDouble(BigDecimal::doubleValue)
                .sum();
    }
}
