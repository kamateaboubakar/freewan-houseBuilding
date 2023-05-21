package com.housebuilding.api.category;

import com.housebuilding.api.material.Material;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "category")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @SequenceGenerator(name = "category_seq", sequenceName = "category_seq")
    @GeneratedValue(generator = "category_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category parent;

    @Column(name = "label", length = 50)
    private String label;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url", length = 2000)
    private String imageUrl;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.PERSIST)
    private List<Category> children = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    private List<Material> materials = new ArrayList<>();

    public Category(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Category category = (Category) o;
        return getCategoryId() != null && Objects.equals(getCategoryId(), category.getCategoryId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
