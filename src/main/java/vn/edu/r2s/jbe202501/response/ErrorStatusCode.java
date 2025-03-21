package vn.edu.r2s.jbe202501.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorStatusCode {
	SUCCESS, 
	NOT_FOUND,
	INTERNAL_SERVER_ERROR,
	INVALID,
	ALREADY_EXIST;
}
