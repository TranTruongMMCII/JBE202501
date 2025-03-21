package vn.edu.r2s.jbe202501.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.r2s.jbe202501.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	List<Role> findByName(String roleName);
}
