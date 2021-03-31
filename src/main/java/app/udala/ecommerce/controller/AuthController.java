package app.udala.ecommerce.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.udala.ecommerce.controller.dto.LoginForm;
import app.udala.ecommerce.controller.dto.TokenDto;
import app.udala.ecommerce.service.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginForm form) {
		UsernamePasswordAuthenticationToken loginData = form.convert();

		try {
			Authentication authenticate = authManager.authenticate(loginData);
			String token = tokenService.generateToken(authenticate);
			
			return ResponseEntity.ok(new TokenDto("Bearer", token));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}

	}
}
