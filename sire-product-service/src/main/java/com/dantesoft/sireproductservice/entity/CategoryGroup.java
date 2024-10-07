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

@Entity
@Table(name = "category_group")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "category_group_id")
	private UUID uuid;
	@Column(name = "group_name", unique = true)
	private String groupName;

	@OneToMany(mappedBy = "categoryGroup")
	private Set<Category> categories;

	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime creationTime;
	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime modificationTime;
}
