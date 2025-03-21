package vn.edu.r2s.jbe202501.security;

import lombok.RequiredArgsConstructor;
import vn.edu.r2s.jbe202501.entity.User;
import vn.edu.r2s.jbe202501.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("Cannot find user with userName=" + username));
		return new CustomUserDetails(user);
	}

}