package com.housebuilding.api.order;

import com.housebuilding.api.material.Material;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;


@Table(name = "ce_order_item")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class OrderItem {
    @Id
    @SequenceGenerator(name = "item_seq", sequenceName = "item_seq")
    @GeneratedValue(generator = "item_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material material;

    private int quantity = 1;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Transient
    public BigDecimal getTotal() {
        return material.getPrice().multiply(BigDecimal.valueOf(this.getQuantity()));
    }

    private Date createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = new Date();
    }
}