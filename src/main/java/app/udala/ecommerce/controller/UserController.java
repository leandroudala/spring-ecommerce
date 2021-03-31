package app.udala.ecommerce.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import app.udala.ecommerce.controller.advice.DuplicatedEntryException;
import app.udala.ecommerce.controller.dto.UserCreateDto;
import app.udala.ecommerce.controller.dto.UserDto;
import app.udala.ecommerce.model.User;
import app.udala.ecommerce.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<UserDto> create(@Valid @RequestBody UserCreateDto form, UriComponentsBuilder uriBuilder) throws DuplicatedEntryException {
		User newUser = userService.save(form.toUser());
		
		String publicId = newUser.getPublicId().toString();
		
		URI location = uriBuilder.path("/user/{publicId}").buildAndExpand(publicId).toUri();

		return ResponseEntity.created(location).body(new UserDto(newUser));
	}

	@GetMapping("/{publicId}")
	public ResponseEntity<UserDto> get(@PathVariable String publicId) {
		Optional<User> found = userService.get(publicId);
		
		if (found.isEmpty())
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(new UserDto(found.get()));
	}
	
	@GetMapping
	public Page<UserDto> list(@PageableDefault(sort = "name", page = 0, size = 10) Pageable pageable) {
		Page<User> users = userService.findAll(pageable);
		return users.map(UserDto::new); 
	}
}
