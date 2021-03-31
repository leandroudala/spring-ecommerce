package app.udala.ecommerce.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import app.udala.ecommerce.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${ecommerce.jwt.expiration}")
	private String expiration;
	
	@Value("${ecommerce.jwt.secret}")
	private String key;

	public String generateToken(Authentication authenticate) {
		User user = (User) authenticate.getPrincipal();
		
		Date today = new Date();
		Date exp = new Date(today.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder()
			.setIssuer("E-commerce")
			.setSubject(user.getId().toString())
			.setIssuedAt(today)
			.setExpiration(exp)
			.signWith(SignatureAlgorithm.HS256, key)
			.compact();
	}
	
	public String gereratePassword(String rawPassword) {
		return new BCryptPasswordEncoder().encode(rawPassword);
	}

	public boolean isValid(String token) {
		try {
			Jwts.parser()
				.setSigningKey(key)
				.parseClaimsJws(token);
			return true;
		} catch(Exception e) {
			return false;			
		}
	}

	public Long getUserId(String token) {
		Claims body = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
		return Long.parseLong(body.getSubject());
	}
	
}
