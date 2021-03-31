package app.udala.ecommerce.config.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import app.udala.ecommerce.model.User;
import app.udala.ecommerce.repository.UserRepository;
import app.udala.ecommerce.service.TokenService;

public class AuthTokenFilter extends OncePerRequestFilter {
	private Logger log = LoggerFactory.getLogger(AuthTokenFilter.class);
	
	private TokenService tokenService;
	
	private UserRepository userRepository;

	public AuthTokenFilter(TokenService tokenService, UserRepository userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = retrieveToken(request);
		boolean isValid = tokenService.isValid(token);
		if (isValid) {
			authenticateUser(token);
		}

		filterChain.doFilter(request, response);
	}

	private void authenticateUser(String token) {
		Long userId = tokenService.getUserId(token);
		Optional<User> getUser = userRepository.findById(userId);
		if (getUser.isPresent()) {
			User user = getUser.get();
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);			
		} else {
			log.error("Failed to retrieve authenticate user by token. UserId: {}", userId);
		}
	}

	private String retrieveToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");

		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}

		return token.substring(7);
	}

}
