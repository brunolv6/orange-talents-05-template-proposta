package br.com.zupacademy.bruno.proposta.compartilhados.errors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException exception) {

		Collection<String> mensagens = new ArrayList<>();
        BindingResult bindingResult = exception.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        
        fieldErrors.forEach(fieldError -> {
            String message = String.format("Field %s %s", fieldError.getField(), fieldError.getDefaultMessage());
            mensagens.add(message);
        });

        ErrorResponse errors = new ErrorResponse(mensagens);
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ErrorResponse> handleApiErroException(ApiException exception) {
	    Collection<String> mensagens = new ArrayList<>();
	    mensagens.add(exception.getReason());

	    ErrorResponse errors = new ErrorResponse(mensagens);
	    return ResponseEntity.status(exception.getHttpStatus()).body(errors);
	}

}
