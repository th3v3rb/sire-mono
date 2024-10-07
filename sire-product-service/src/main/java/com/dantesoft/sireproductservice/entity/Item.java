package com.dantesoft.sireproductservice.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.dantesoft.sireproductservice.domain.UnitMeasure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "items")
@AllArgsConstructor
@NoArgsConstructor
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "item_id")
	private UUID uuid;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "image_url", nullable = true)
	private String imageUrl;
	@Column(name = "bar_code", nullable = true)
	private String barCode;

	@Column(name = "status")
	private boolean active;

	@Column(name = "unit_measure")
	private UnitMeasure unitMeasure;

	@Column(name = "buy_price")
	private BigDecimal buyPrice;

	@Column(name = "sell_price")
	private BigDecimal sellPrice;

	@ManyToMany
	@JoinTable(name = "item_has_categories", joinColumns = @JoinColumn(name = "item_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories;

	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;

	@CreationTimestamp
	@Column(name = "created_at")
	private LocalDateTime creationTime;
	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime modificationTime;
}
