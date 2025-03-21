package vn.edu.r2s.jbe202501.exception;

import vn.edu.r2s.jbe202501.response.ErrorStatusCode;

public class UserAlreadyExistException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserAlreadyExistException(String errorCode, String message) {
		super(ErrorStatusCode.ALREADY_EXIST, errorCode, message);
	}
}
