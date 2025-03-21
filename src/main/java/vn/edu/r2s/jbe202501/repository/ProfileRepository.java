package vn.edu.r2s.jbe202501.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.r2s.jbe202501.entity.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
