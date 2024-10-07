package com.dantesoft.sireproductservice.entity;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
public class Category {
	@Id
	@Column(name = "category_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID uuid;
	@Column(name = "name", nullable = false, length = 30)
	private String name;
	@Column(name = "status", nullable = false)
	private boolean active;

	@ManyToMany(mappedBy = "categories")
	private Set<Item> items;

	@ManyToOne
	@JoinColumn(name = "category_group_id")
	private CategoryGroup categoryGroup;

	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime creationTime;
	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime modificationTime;
}
