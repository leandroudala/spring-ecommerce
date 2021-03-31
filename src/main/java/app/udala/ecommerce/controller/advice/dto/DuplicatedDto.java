package app.udala.ecommerce.controller.advice.dto;

import java.util.List;

public class DuplicatedDto {
	private final String message;
	private final List<String> fields;
	
	public DuplicatedDto(String message, List<String> fields) {
		this.message = message;
		this.fields = fields;
	}
	public String getMessage() {
		return message;
	}

	public List<String> getFields() {
		return fields;
	}

}
