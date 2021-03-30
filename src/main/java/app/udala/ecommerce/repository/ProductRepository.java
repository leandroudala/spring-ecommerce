package app.udala.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.udala.ecommerce.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
