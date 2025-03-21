package vn.edu.r2s.jbe202501.response;

import java.time.OffsetDateTime;
import java.util.Map;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

	private final OperationType operationType = OperationType.FAILURE;
	private ErrorStatusCode statusCode;
	private String errorCode;
	private String message;
	private Map<String, Object> details;
	private final OffsetDateTime timestamp = OffsetDateTime.now();
	
	public static ErrorResponse of(final ErrorStatusCode statusCode, final String errorCode, final String message) {
		return ErrorResponse.builder()
				.statusCode(statusCode)
				.errorCode(errorCode)
				.message(message)
				.build();
	}
	
	public static ErrorResponse of(final ErrorStatusCode statusCode, final String errorCode, final String message, final Map<String, Object> details) {
		return ErrorResponse.builder()
				.statusCode(statusCode)
				.errorCode(errorCode)
				.message(message)
				.details(details)
				.build();
	}
}
