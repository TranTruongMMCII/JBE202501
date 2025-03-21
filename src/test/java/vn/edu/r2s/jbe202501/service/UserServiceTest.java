package vn.edu.r2s.jbe202501.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import vn.edu.r2s.jbe202501.entity.User;
import vn.edu.r2s.jbe202501.exception.UserNotFoundException;
import vn.edu.r2s.jbe202501.repository.RoleRepository;
import vn.edu.r2s.jbe202501.repository.UserRepository;

@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@MockitoBean
	private UserRepository userRepository;

	@MockitoBean
	private PasswordEncoder passwordEncoder;

	@MockitoBean
	private RoleRepository roleRepository;

	private static final String USER_NAME = "user01Test";
	private static final Long USER_ID = -1L;
	private final User user01 = User.builder()
			.userName(USER_NAME)
			.id(USER_ID)
			.build();
	
	@Test
	void testFindByIdSuccess() {
		// given
		when(this.userRepository.findById(eq(USER_ID))).thenReturn(Optional.of(user01));
		
		// when
		User givenUser = this.userService.findById(USER_ID);
		
		// then
		assertNotNull(givenUser);
		assertEquals(USER_ID, givenUser.getId());
		assertEquals(USER_NAME, givenUser.getUserName());
		verify(this.userRepository, times(1)).findById(USER_ID);
	}
	
	@Test
	void testFindByIdNotFound() {
		// given
		when(this.userRepository.findById(anyLong())).thenReturn(Optional.empty());
		
		// when
		Executable result = () -> this.userService.findById(USER_ID);
		
		// then
		UserNotFoundException e = assertThrows(UserNotFoundException.class, result);
		assertEquals("user", e.getErrorCode());
		assertEquals("Cannot find user by id: -1", e.getMessage());
		verify(this.userRepository, times(1)).findById(USER_ID);
	}
}
