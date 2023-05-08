package com.housebuilding.api.order;

import com.housebuilding.api.order.paymentdetail.PaymentDetail;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static com.housebuilding.api.order.OrderStatus.CANCELED;
import static com.housebuilding.api.order.OrderStatus.PENDING;
import static com.housebuilding.api.order.OrderStatus.VALIDATED;

@Table(name = "ce_order_detail")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "customer_account_id")
    private String customerAccountId;

    @OneToOne
    @JoinColumn(name = "payment_detail_id")
    private PaymentDetail paymentDetail;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "delivery_address_id")
    private String deliveryAddressId;

    private LocalDate deliveryDate;

    private Date createdAt;

    private OrderStatus status = PENDING;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems = new HashSet<>();

    @PrePersist
    public void prePersist() {
        createdAt = new Date();
    }

    @Transient
    public BigDecimal getTotalOrderItem() {
        return orderItems.stream()
                .map(OrderItem::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    @Transient
    public boolean isPending() {
        return status.equals(PENDING);
    }

    @Transient
    public boolean isValidated() {
        return status.equals(VALIDATED);
    }

    @Transient
    public boolean isCanceled() {
        return status.equals(CANCELED);
    }
}