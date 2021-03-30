package app.udala.ecommerce.controller.dto;

import java.util.UUID;

import org.springframework.data.domain.Page;

import app.udala.ecommerce.model.Product;

public class ProductDto {
	private final UUID publicId;
	private final String name;
	private final String description;
	private final Long price;
	private String photo;

	public ProductDto(Product product) {
		this.publicId= product.getPublicId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice();
		this.photo = product.getPhoto();
	}

	public UUID getPublicId() {
		return publicId;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Long getPrice() {
		return price;
	}

	public String getPhoto() {
		return photo;
	}

	public static Page<ProductDto> getPageable(Page<Product> products) {
		return products.map(ProductDto::new);
	}

}
