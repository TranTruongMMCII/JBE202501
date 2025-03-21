package vn.edu.r2s.jbe202501.controller;

import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import vn.edu.r2s.jbe202501.dto.request.SignInRequest;
import vn.edu.r2s.jbe202501.dto.request.SignUpRequest;
import vn.edu.r2s.jbe202501.dto.response.SignInResponse;
import vn.edu.r2s.jbe202501.entity.User;
import vn.edu.r2s.jbe202501.mapper.UserMapper;
import vn.edu.r2s.jbe202501.response.SuccessResponse;
import vn.edu.r2s.jbe202501.security.CustomUserDetails;
import vn.edu.r2s.jbe202501.service.UserService;
import vn.edu.r2s.jbe202501.utils.JwtUtils;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/auth")
public class AuthController {

	private final UserMapper userMapper;
	private final UserService userService;
	private final AuthenticationManager authenticationManager;
	private final JwtUtils jwtUtils;

	@PostMapping(path = "/signUp")
	public SuccessResponse<Boolean> signUp(@RequestBody SignUpRequest request) {
		User user = this.userMapper.toModel(request);
//		log.info("{}", user.getUserName());
		return SuccessResponse.of(this.userService.signUp(user));
	}
	
	@PostMapping(path = "/signIn")
	public SuccessResponse<SignInResponse> signIn(@RequestBody SignInRequest request) {
		Authentication authentication = this.authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));

        // Retrieve user details from the authenticated token
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        // Generate JWT token
        String accessToken = this.jwtUtils.generateToken(userDetails);
        Date expriedDate = this.jwtUtils.extractExpiration(accessToken);

        return SuccessResponse.of(SignInResponse.builder()
        		.token(accessToken)
        		.expiredDate(expriedDate)
        		.build());
	}
}
