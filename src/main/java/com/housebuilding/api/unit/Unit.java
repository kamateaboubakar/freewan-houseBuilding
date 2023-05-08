package com.housebuilding.api.unit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "unit")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Unit {
    @Id
    @SequenceGenerator(name = "unit_seq", sequenceName = "unit_seq")
    @GeneratedValue(generator = "unit_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "unit_id", nullable = false)
    private Long unitId;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "label", nullable = false)
    private String label;

    public Unit(Long unitId) {
        this.unitId = unitId;
    }

    public Unit(String code, String label) {
        this.code = code;
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Unit unit = (Unit) o;
        return getUnitId() != null && Objects.equals(getUnitId(), unit.getUnitId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
