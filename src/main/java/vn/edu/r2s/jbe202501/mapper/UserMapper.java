package vn.edu.r2s.jbe202501.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import vn.edu.r2s.jbe202501.dto.request.SignUpRequest;
import vn.edu.r2s.jbe202501.dto.request.UserRequestDTO;
import vn.edu.r2s.jbe202501.dto.response.UserResponseDTO;
import vn.edu.r2s.jbe202501.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	@Mapping(source = "user.profile.identityNumber", target = "identityNumber")
	UserResponseDTO convertToDTO(final User user);

	@Mapping(source = "identityNumber", target = "profile.identityNumber")
	User convertToModel(final UserRequestDTO dto);

	@Mappings(value = { 
			@Mapping(source = "displayName", target = "name"),
			@Mapping(source = "identityNumber", target = "profile.identityNumber") 
	})
	User toModel(final SignUpRequest dto);
}
