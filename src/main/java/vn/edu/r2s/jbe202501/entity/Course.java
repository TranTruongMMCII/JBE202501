package vn.edu.r2s.jbe202501.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // This tells Hibernate to make a table out of this class
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
//	@ManyToMany(mappedBy = "courses")
//	private List<User> users = new ArrayList<User>();
	
//	@OneToMany(mappedBy = "course")
//	private List<UserCourseComposite> list;
	
	@OneToMany(mappedBy = "course")
	private List<UserCourseNewKey> list;
}
