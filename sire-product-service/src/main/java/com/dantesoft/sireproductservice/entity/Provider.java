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
@Table(name = "providers")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Provider {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "provider_id")
	private UUID uuid;
	@Column(name = "social_reason")
	private String socialReason;
	@Column(name = "document")
	private String document;

	@ManyToOne
	@JoinColumn(name = "document_type_id")
	private DocumentType documentType;

	@ManyToMany
	@JoinTable(name = "provider_has_items", joinColumns = @JoinColumn(name = "provider_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
	private Set<Item> providedItems;

	@CreationTimestamp
	@Column(name = "created_at")
	private LocalDateTime creationTime;
	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime modificationTime;
}
