package vn.edu.r2s.jbe202501.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.edu.r2s.jbe202501.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByName(final String name);

	List<User> findByNameContains(final String name, final PageRequest pageRequest);

	List<User> findByNameContaining(final String name);

	@Query(nativeQuery = true, value = "SELECT * FROM jbe202501.user where name like ?1")
	List<User> findByNameQuery(final String name);

	Optional<User> findByUserName(String username);
}
