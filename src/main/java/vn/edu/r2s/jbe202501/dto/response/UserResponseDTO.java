package vn.edu.r2s.jbe202501.dto.response;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.r2s.jbe202501.entity.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {

	private String name;
	private String email;
	private String identityNumber;

	public static UserResponseDTO convertToDTO(final User user) {
		UserResponseDTO dto = new UserResponseDTO();

		if (Objects.nonNull(user)) {
			dto.setName(user.getName());
			dto.setEmail(user.getEmail());

			if (Objects.nonNull(user.getProfile())) {
				dto.setIdentityNumber(user.getProfile().getIdentityNumber());
			}
		}

		return dto;
	}
}
