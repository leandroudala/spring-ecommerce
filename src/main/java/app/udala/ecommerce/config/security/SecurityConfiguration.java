package app.udala.ecommerce.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import app.udala.ecommerce.repository.UserRepository;
import app.udala.ecommerce.service.AuthService;
import app.udala.ecommerce.service.TokenService;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Autowired
	private AuthService authService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authService).passwordEncoder(new BCryptPasswordEncoder());
	}

	// setup urls
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/products").permitAll()
			.antMatchers(HttpMethod.GET, "/products/*").permitAll()
			.antMatchers(HttpMethod.POST, "/auth").permitAll()
			.antMatchers(HttpMethod.POST, "/user").permitAll()
			.antMatchers(HttpMethod.GET, "/user/*").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/user").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and().csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().addFilterBefore(new AuthTokenFilter(tokenService, userRepository), UsernamePasswordAuthenticationFilter.class);
	}
	
	// static resources
	@Override
	public void configure(WebSecurity web) throws Exception {
		
	}
	
}
