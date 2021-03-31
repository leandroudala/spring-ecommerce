package app.udala.ecommerce.controller.advice;

import java.util.ArrayList;
import java.util.List;

import app.udala.ecommerce.model.User;

public class DuplicatedEntryException extends Exception {
	private static final long serialVersionUID = 1L;

	private final List<String> fields;
	
	public DuplicatedEntryException(User form, User saved) {
		super("Por favor, verifique os dados inseridos. Algumas informações já estão sendo usadas por outro usuário.");

		fields = new ArrayList<>();
		if (form.getEmail().equals(saved.getEmail())) {
			fields.add("email");
		}

		if (form.getCpf().equals(saved.getCpf())) {
			fields.add("cpf");
		}

		if (form.getPhone().equals(saved.getPhone())) {
			fields.add("phone");
		}
	}
	
	public final List<String> getFields() {
		return fields;
	}

}
