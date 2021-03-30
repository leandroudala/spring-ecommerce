package app.udala.ecommerce.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 16, nullable = false, unique = true)
	private UUID publicId;

	@Column(nullable = false)
	private String name;

	private String description;

	@Column(nullable = false)
	private Long price;

	@Column(nullable = false)
	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
	private LocalDateTime deletedAt;

	private String photo;

	@OneToMany
	private List<Stock> stock;

	@PrePersist
	public void beforeInsert() {
		createdAt = LocalDateTime.now();
	}

	@PreUpdate
	public void beforeUpdate() {
		updatedAt = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UUID getPublicId() {
		return publicId;
	}

	public void setPublicId(UUID publicId) {
		this.publicId = publicId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public List<Stock> getStock() {
		return stock;
	}

	public void setStock(List<Stock> stock) {
		this.stock = stock;
	}

}
