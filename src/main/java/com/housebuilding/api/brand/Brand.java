package com.housebuilding.api.brand;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "brand")
public class Brand {
    @Id
    @SequenceGenerator(name = "brand_seq", sequenceName = "brand_seq")
    @GeneratedValue(generator = "brand_seq", strategy = GenerationType.SEQUENCE)
    private Long brandId;

    @Column(name = "label", nullable = false)
    private String label;
}
