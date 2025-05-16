package com.github.lotashinski.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Setter(AccessLevel.PROTECTED)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt = LocalDateTime.now();
	
	@Column(name = "customer_name")
	private String customerName;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "is_finished")
	private Boolean isFinished;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "id.order")
	private List<OrderItem> items = new ArrayList<>();
	
	public void putItem(Product product, int count) {
		findItem(product)
			.ifPresentOrElse(i -> i.setCount(count), () -> items.add(new OrderItem(product, this, count)));
	}
	
	public void purgeItem(Product product) {
		findItem(product)
			.ifPresent(i -> items.remove(i));
	}
	
	private Optional<OrderItem> findItem(Product product) {
		return items.stream()
				.filter(i -> i.getProduct().equals(product))
				.findFirst();
	}
	
}
