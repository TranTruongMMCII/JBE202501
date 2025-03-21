package vn.edu.r2s.jbe202501.handler;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;
import vn.edu.r2s.jbe202501.exception.NotFoundException;
import vn.edu.r2s.jbe202501.response.ErrorResponse;
import vn.edu.r2s.jbe202501.response.ErrorStatusCode;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(exception = NotFoundException.class)
	public ErrorResponse handleNotFoundException(final NotFoundException ex) {
		return ErrorResponse.of(ex.getStatusCode(), ex.getErrorCode(), ex.getMessage());
	}
	
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(exception = Exception.class)
	public ErrorResponse handleException(final Exception ex) {
		ex.printStackTrace();
		return ErrorResponse.of(ErrorStatusCode.INTERNAL_SERVER_ERROR, "server", ex.getMessage());
	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(exception = ConstraintViolationException.class)
	public ErrorResponse handleException(final ConstraintViolationException ex) {
		Map<String, Object> errors = ex.getConstraintViolations()
				.stream()
				.collect(Collectors.toMap(x->x.getPropertyPath().toString(), x->x.getMessage()));
		return ErrorResponse.of(ErrorStatusCode.INVALID, "server", "", errors);
	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(exception = MethodArgumentNotValidException.class)
	public ErrorResponse handleException(final MethodArgumentNotValidException ex) {
		Map<String, Object> errors = ex.getBindingResult().getFieldErrors()
				.stream()
				.collect(Collectors.toMap(x->x.getField(), x->x.getDefaultMessage()));
		return ErrorResponse.of(ErrorStatusCode.INVALID, "server", "", errors);
	}
}
