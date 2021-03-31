package app.udala.ecommerce.controller.dto;

public class TokenDto {
	private String type;
	private String token;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public TokenDto(String type, String token) {
		this.token = token;
		this.type = type;
	}
}