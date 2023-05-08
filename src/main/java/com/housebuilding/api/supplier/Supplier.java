package com.housebuilding.api.supplier;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "supplier")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    @Id
    @GeneratedValue
    @Column(name = "supplier_id", nullable = false)
    private UUID supplierId;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "basic_info", length = 1000)
    private String basicInfo;

    @Column(name = "address")
    private String address;

    @Column(name = "contact")
    private String contact;

    @Column(name = "verified")
    private boolean verified = false;

    @Column(name = "official")
    private boolean official = false;

    public Supplier(UUID supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Supplier supplier = (Supplier) o;
        return getSupplierId() != null && Objects.equals(getSupplierId(), supplier.getSupplierId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
