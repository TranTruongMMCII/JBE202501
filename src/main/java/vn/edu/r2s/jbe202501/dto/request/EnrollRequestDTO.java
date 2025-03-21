package vn.edu.r2s.jbe202501.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnrollRequestDTO {

	@NotNull
	private Long userId;
	
	@NotNull
	private Long classRoomId;
}
