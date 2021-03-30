package app.udala.ecommerce.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.validator.constraints.Range;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Long total;
	
	@Range(min = 0)
	private Long shipping;

	@Column(nullable = false)
	private CartStatus status;

	@Column(nullable = false)
	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
	
	@ManyToOne
	private User user;
	
	@ManyToMany
	private List<Product> products;

	@PrePersist
	public void beforeInsert() {
		createdAt = LocalDateTime.now();
		status = CartStatus.CREATED;
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

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Long getShipping() {
		return shipping;
	}

	public void setShipping(Long shipping) {
		this.shipping = shipping;
	}

	public CartStatus getStatus() {
		return status;
	}

	public void setStatus(CartStatus status) {
		this.status = status;
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

}
