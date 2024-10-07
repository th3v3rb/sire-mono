package com.dantesoft.sireproductservice.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "brands")
@AllArgsConstructor
@NoArgsConstructor
public class Brand {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "brand_id", nullable = false)
	private UUID uuid;
	@Column(name = "name", nullable = false, length = 30)
	private String name;
	@Column(name = "description", nullable = true, length = 240)
	private String description;
	@Column(name = "status", nullable = false)
	private boolean active;

	@Column(name = "created_at")
	@CreationTimestamp()
	private LocalDateTime creationTime;
	@Column(name = "updated_at")
	@UpdateTimestamp()
	private LocalDateTime modificationTime;
}
