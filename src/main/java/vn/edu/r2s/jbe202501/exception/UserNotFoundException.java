package vn.edu.r2s.jbe202501.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends NotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String errorCode, String message) {
		super(errorCode, message);
	}
}
