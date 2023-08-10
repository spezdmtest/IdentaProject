package com.identa.identaproject.dto;

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
    private int amount;
    private Double sum;

    public List<BucketDetailDTO> details = new ArrayList<>();

    public void calc() {
        this.amount = details.size();
        this.sum = details.stream()
                .map(BucketDetailDTO::getSum)
                .mapToDouble(BigDecimal::doubleValue)
                .sum();
    }
}
