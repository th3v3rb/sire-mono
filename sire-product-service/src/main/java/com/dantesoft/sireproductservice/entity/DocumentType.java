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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "document_types")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentType {
	@Id
	@Column(name = "document_type_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID uuid;
	@Column(name = "type", unique = true)
	private String document;

	@OneToMany(mappedBy = "documentType")
	private Set<Provider> providers;

	@CreationTimestamp
	@Column(name = "created_at")
	private LocalDateTime creationTime;
	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime modificationTime;
}
