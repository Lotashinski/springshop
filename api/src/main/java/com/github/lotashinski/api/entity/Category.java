package com.github.lotashinski.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Setter;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Entity
@Table(name = "category")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Category {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Setter(AccessLevel.PROTECTED)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(name = "title", nullable = false)
	private String title;
	
}
