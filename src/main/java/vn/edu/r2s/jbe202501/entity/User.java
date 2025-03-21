package vn.edu.r2s.jbe202501.entity;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
@Schema(name = "ModelUser", description = "Handle request and response involved with user!")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Hidden
	private Long id;

	@NotBlank(message = "Ten khong duoc de trong")
	private String name;

	@Email(message = "Email phai dung dinh dang")
	private String email;
	
	private Integer age;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "profile_id", referencedColumnName = "id")
	private Profile profile;
	
	@ManyToOne
	@JoinColumn(name = "class_room_id", referencedColumnName = "id")
	private ClassRoom classRoom;
	
	@Column(unique = true)
	private String userName;
	
	private String password;
	
	private Boolean isDeleted;
	
//	@ManyToMany
//	@JoinTable(name = "user_course", 
//	joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
//	inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
//	private List<Course> courses = new ArrayList<Course>();
	
//	@OneToMany(mappedBy = "user")
//	private List<UserCourseComposite> list;
	
	@OneToMany(mappedBy = "user")
	private List<UserCourseNewKey> list;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "role_user", 
		joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private List<Role> roles = new ArrayList<>();
}