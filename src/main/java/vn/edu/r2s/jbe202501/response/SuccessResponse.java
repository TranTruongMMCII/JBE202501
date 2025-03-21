package vn.edu.r2s.jbe202501.response;

import java.time.OffsetDateTime;
import java.util.Collection;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SuccessResponse<T> {

	private final OperationType operationType = OperationType.SUCCESS;
	private final int statusCode = 200;
	private final String message = "success";
	private T content;
	private int page;
	private int size;
	private final OffsetDateTime timestamp = OffsetDateTime.now();

	public static <T> SuccessResponse<T> of(final T content) {
		boolean isCollection = false;
		if (content != null && content instanceof Collection<?>) {
			isCollection = true;
		}

		return SuccessResponse.<T>builder()
				.content(content)
				.size(isCollection ? ((Collection<?>) content).size() : 0)				
				.build();
	}
}
