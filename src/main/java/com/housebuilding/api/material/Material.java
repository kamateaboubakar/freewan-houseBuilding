package com.housebuilding.api.material;

import com.housebuilding.api.brand.Brand;
import com.housebuilding.api.category.Category;
import com.housebuilding.api.supplier.Supplier;
import com.housebuilding.api.unit.Unit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "material")
@Getter
@Setter
@NoArgsConstructor
public class Material {
    @Id
    @SequenceGenerator(name = "material_seq", sequenceName = "material_seq")
    @GeneratedValue(generator = "material_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "material_id", nullable = false)
    private Long materialId;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(optional = false)
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price = BigDecimal.ZERO;

    @ManyToOne(optional = false)
    @JoinColumn(name = "unit_id", nullable = false)
    private Unit unit;

    @Column(name = "image_url", length = 2000)
    private String imageUrl;

    public Material(Long materialId) {
        this.materialId = materialId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Material material = (Material) o;
        return getMaterialId() != null && Objects.equals(getMaterialId(), material.getMaterialId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
