package vn.edu.r2s.jbe202501.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vn.edu.r2s.jbe202501.response.ErrorStatusCode;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BaseException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ErrorStatusCode statusCode;
	private String errorCode;
	private String message;
}
