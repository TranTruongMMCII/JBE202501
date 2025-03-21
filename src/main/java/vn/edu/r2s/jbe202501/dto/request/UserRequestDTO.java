package vn.edu.r2s.jbe202501.dto.request;

import java.util.Objects;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.r2s.jbe202501.entity.Profile;
import vn.edu.r2s.jbe202501.entity.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDTO {

	@NotBlank
	private String name;
	
	@NotBlank
	@Min(value = 10L)
	@Max(value = 100L)
	private String email;
	private String identityNumber;

	public static User convertToModel(final UserRequestDTO dto) {
		if (Objects.isNull(dto)) {
			return null;
		}
		
		return User.builder()
				.name(dto.getName())
				.email(dto.getEmail())
				.profile(Profile.builder()
						.identityNumber(dto.getIdentityNumber())
						.build())
				.build();
	}
}
