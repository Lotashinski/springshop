package com.github.lotashinski.api.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "`order`")
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
	@Setter(AccessLevel.PROTECTED)
	private LocalDateTime createdAt;
	
	@Column(name = "customer_name")
	private String customerName;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "is_finished")
	private Boolean isFinished;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "id.order")
	@Setter(AccessLevel.PROTECTED)
	private Set<OrderItem> items = new HashSet<>();
	
	public BigDecimal getCost() {
		return items.stream()
				.map(i -> i.getCostOfOne().multiply(new BigDecimal(i.getCount())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
	public void putItem(Product product, int count) {		
		items.add(new OrderItem(product, this, count));
	}
	
	public void purgeItem(Product product) {
		items.remove(new OrderItem(product, this, 0));
	}
	
	@PrePersist
	protected void updateTimestamp() {
		createdAt = LocalDateTime.now();
	}
	
}
