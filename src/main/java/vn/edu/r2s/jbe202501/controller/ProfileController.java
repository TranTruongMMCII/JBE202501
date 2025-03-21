package vn.edu.r2s.jbe202501.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import vn.edu.r2s.jbe202501.entity.Profile;
import vn.edu.r2s.jbe202501.repository.ProfileRepository;
import vn.edu.r2s.jbe202501.response.SuccessResponse;

@RequestMapping(path = "/profiles")
@RestController
@RequiredArgsConstructor
public class ProfileController {

	private final ProfileRepository profileRepository;

	@GetMapping(path = "/{id}")
	public SuccessResponse<Profile> getById(@PathVariable(name = "id") Long id) {
		return SuccessResponse.of(this.profileRepository.findById(id).get());
	}
}
