package app.udala.ecommerce.controller.advice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import app.udala.ecommerce.controller.dto.ErrorDto;

@RestControllerAdvice
public class ValidationHandler {

	@Autowired
	private MessageSource messageSource;
	
	private Logger log = LoggerFactory.getLogger(ValidationHandler.class);
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	public List<ErrorDto> handler(BindException exception) {
		List<ErrorDto> errors = new ArrayList<>();
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String error = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			errors.add(new ErrorDto(e.getField(), error));
		});
		
		return errors;
	}
	
	@ResponseStatus(code = HttpStatus.CONFLICT)
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public void handlerSql(SQLIntegrityConstraintViolationException exception) {
		int errorCode = exception.getErrorCode();
		String message = exception.getMessage();
		
		switch(errorCode) {
			case 1062:
				
				break;
		}
		log.error("Error while inserting on database: [{}] {}", errorCode, message);
	}
}
