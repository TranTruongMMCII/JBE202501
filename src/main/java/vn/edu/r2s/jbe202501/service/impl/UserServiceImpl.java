package vn.edu.r2s.jbe202501.service.impl;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.edu.r2s.jbe202501.constant.SecurityRole;
import vn.edu.r2s.jbe202501.entity.User;
import vn.edu.r2s.jbe202501.exception.NotFoundException;
import vn.edu.r2s.jbe202501.exception.UserAlreadyExistException;
import vn.edu.r2s.jbe202501.exception.UserNotFoundException;
import vn.edu.r2s.jbe202501.repository.RoleRepository;
import vn.edu.r2s.jbe202501.repository.UserRepository;
import vn.edu.r2s.jbe202501.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;

	@Override
	public List<User> getAll(Pageable pageable) {
		return this.userRepository.findAll(pageable).stream().toList();
	}

	@Override
	public User getById(Long id) {
		User foundUser = this.userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("user", "Cannot find user by id: " + id));
//		System.err.println(foundUser.getProfile());
		return foundUser;
	}

	@Override
	public User insertUser(User user) {
		return this.userRepository.save(user);
	}

	@Override
	public User updateUser(Long id, User newUser) {
		User foundUser = this.userRepository.findById(id).orElse(null);
		if (foundUser == null) {
			return null;
		}

		foundUser.setEmail(newUser.getEmail());
		foundUser.setName(newUser.getName());
		return this.userRepository.save(foundUser);
	}

	@Override
	public Boolean deleteUser(Long id) {
		User foundUser = this.userRepository.findById(id).orElse(null);
		if (foundUser == null) {
			return false;
		}

		this.userRepository.delete(foundUser);
		return true;
	}

	@Override
	public List<User> findByName(String name, PageRequest pageRequest) {
		return this.userRepository.findByNameContains(name, pageRequest);
	}

	@Override
	public Boolean signUp(User user) {
		// check exists userName
		this.userRepository.findByUserName(user.getUserName())
		.ifPresent((u) -> {
			throw new UserAlreadyExistException("auth.signup", "User with userName=%s already existed!".formatted(u.getUserName()));
		});
		
		user.setIsDeleted(false);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		user.setRoles(this.roleRepository.findByName(SecurityRole.ROLE_USER));
		this.userRepository.save(user);
		return true;
	}

	@Override
	public User findById(Long userId) {
		return this.userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("user", "Cannot find user by id: " + userId));
	}
}
