package app.udala.ecommerce.controller.dto;

import app.udala.ecommerce.model.Product;

public class ProductForm {
	private String name;
	private String description;
	private Long price;

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

	public Product toProduct() {
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setDescription(description);
		return product;
	}

}
