package com.housebuilding.api.order.paymentdetail;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.housebuilding.api.order.Order;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Table(name = "payment_detail")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetail {
    @Id
    @GeneratedValue
    private UUID id;

    @JsonIgnore
    @OneToOne(mappedBy = "paymentDetail")
    private Order order;

    @Column(name = "provider")
    private String provider;

    @Column(name = "reference")
    private String reference;

    @Column(name = "amount")
    private String amount;

    @Column(name = "payment_fees")
    private double paymentFees = 0D;

    @Column(name = "delivery_fees")
    private double deliveryFees = 0D;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus status = PaymentStatus.PENDING;

    private Date createdAt;

    private Date updatedAt;

    @PreUpdate
    public void preUpdate() {
        updatedAt = new Date();
    }

    @PrePersist
    public void prePersist() {
        createdAt = new Date();
    }
}
