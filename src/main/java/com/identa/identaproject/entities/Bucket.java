package com.identa.identaproject.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "buckets")
public class Bucket {

    private static final String SEQ_NAME = "bucket_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "buckets_products",
               joinColumns = @JoinColumn(name = "bucket_id"),
               inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;
}
