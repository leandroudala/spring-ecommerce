package app.udala.ecommerce.controller.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import app.udala.ecommerce.controller.advice.dto.DuplicatedDto;

@RestControllerAdvice
public class DuplicatedEntryHandler {
	@Autowired MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.CONFLICT)
	@ExceptionHandler(DuplicatedEntryException.class)
	public DuplicatedDto handler(DuplicatedEntryException exception) {
		return new DuplicatedDto(exception.getMessage(), exception.getFields());
	}
}
