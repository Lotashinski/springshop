package com.github.lotashinski.api.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Setter(AccessLevel.PROTECTED)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "cost", nullable = false, precision = 10, scale = 2)
	private BigDecimal cost;

	@ManyToMany
	@JoinTable(name = "product_category",
		joinColumns = @JoinColumn(name = "product_id"),
		inverseJoinColumns = @JoinColumn(name = "category_id"))
	@Setter(AccessLevel.PROTECTED)
	private Set<Category> categories = new HashSet<>();

	@Column(name = "is_deleted")
	private Boolean isDeleted;
	
	public void addCategory(Category category) {
		if (categories.contains(category)) return;
		
		categories.add(category);
		category.addProduct(this);
	}

	public void removeCategory(Category category) {
		if (! categories.contains(category)) return;
		
		categories.remove(category);
		category.removeProduct(this);
	}

}
