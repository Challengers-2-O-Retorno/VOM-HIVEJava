package com.example.VOM_HiveJava.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "TB_PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PRODUCT")
    @SequenceGenerator(name = "SQ_PRODUCT", sequenceName = "SQ_PRODUCT", allocationSize = 1)
    @Column(name = "ID_PRODUCT")
    private int id_product;

    @Column(name = "NM_PRODUCT")
    private String nm_product;

    @Column(name = "CATEGORY_PRODUCT")
    private String category;
}
