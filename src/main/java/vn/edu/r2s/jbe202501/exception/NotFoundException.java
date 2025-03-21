package vn.edu.r2s.jbe202501.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import vn.edu.r2s.jbe202501.response.ErrorStatusCode;

@Getter
@AllArgsConstructor
public class NotFoundException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundException(String errorCode, String message) {
		super(ErrorStatusCode.NOT_FOUND, errorCode, message);
	}
}
