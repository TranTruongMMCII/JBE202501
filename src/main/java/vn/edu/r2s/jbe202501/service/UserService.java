package vn.edu.r2s.jbe202501.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import vn.edu.r2s.jbe202501.entity.User;

public interface UserService {

	List<User> getAll(Pageable pageable);

	User getById(Long id);

	User insertUser(User user);

	User updateUser(Long id, User newUser);

	Boolean deleteUser(Long id);
	
	List<User> findByName(final String name, final PageRequest pageRequest);
	
	Boolean signUp(User user);

	User findById(Long userId);
}
