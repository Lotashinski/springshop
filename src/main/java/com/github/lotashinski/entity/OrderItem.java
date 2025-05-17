package com.github.lotashinski.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderItem {

	@Embeddable
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor(access = AccessLevel.PROTECTED)
	@EqualsAndHashCode
	@Getter
	@Setter(AccessLevel.PROTECTED)
	public static class OrderItemPk {

        @ManyToOne
        private Product product;

        @ManyToOne
        private Order order;

    }
	
	@EmbeddedId
	@AttributeOverrides({
        @AttributeOverride(
                name = "product",
                column = @Column(name = "product_id", nullable = false)
        ),
        @AttributeOverride(
                name = "order",
                column = @Column(name = "order_id", nullable = false)
        )
    })
	@EqualsAndHashCode.Include
	@Setter(AccessLevel.PROTECTED)
	private OrderItemPk id;
	
	@Column(name = "count", nullable = false)
	private Integer count;
	
	OrderItem(Product product, Order order, int count) {
		this.id = new OrderItemPk(product, order);
		this.count = count;
	}
	
	public Product getProduct() {
		return id.getProduct();
	}
	
	public Order getOrder() {
		return id.getOrder();
	}
	
}
