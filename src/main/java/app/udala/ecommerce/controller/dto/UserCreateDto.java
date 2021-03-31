package app.udala.ecommerce.controller.dto;

import javax.validation.constraints.NotEmpty;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import app.udala.ecommerce.model.User;

public class UserCreateDto {
	@NotEmpty
	private String name;

	@NotEmpty
	private String cpf;

	@NotEmpty
	private String email;

	@NotEmpty
	private String password;

	private String phone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	
	public User toUser() {
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPhone(phone);
		user.setCpf(cpf);
		user.setPassword(new BCryptPasswordEncoder().encode(password));
		return user;
	}
}
