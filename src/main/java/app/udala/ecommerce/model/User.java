package app.udala.ecommerce.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotEmpty;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@NotEmpty
	private String name;
	
	@Column(nullable = false, unique = true, length = 16)
	private UUID publicId;

	@Column(nullable = false, unique = true)
	@NotEmpty
	private String email;

	@Column(nullable = false, unique = true, length = 11)
	@NotEmpty
	private String cpf;

	@Column(nullable = false)
	private String password;

	@Column(unique = true)
	private String phone;
	
	@OneToMany
	@JoinColumn(name = "cart_id")
	private List<Cart> carts;
	
	@OneToMany
	@JoinColumn(name = "address_id")
	private List<Address> addresses;

	@Column(nullable = false)
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime deletedAt;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

}
