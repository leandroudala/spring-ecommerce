package app.udala.ecommerce.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import app.udala.ecommerce.controller.dto.ProductDto;
import app.udala.ecommerce.controller.dto.ProductForm;
import app.udala.ecommerce.model.Product;
import app.udala.ecommerce.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Cacheable(value = "get-products")
	@GetMapping
	public Page<ProductDto> list(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable pageable) {
		Page<Product> products = productRepository.findAll(pageable);
		return ProductDto.getPageable(products);
	}
	
	public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductForm form, UriComponentsBuilder uriBuilder) {
		Product product = form.toProduct();
		
		productRepository.save(product);
		
		URI uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId().toString()).toUri();
		return ResponseEntity.created(uri).body(new ProductDto(product));
	}
}
