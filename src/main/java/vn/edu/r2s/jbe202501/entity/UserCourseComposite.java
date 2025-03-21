package vn.edu.r2s.jbe202501.entity;

import java.util.List;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity // This tells Hibernate to make a table out of this class
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Table(name = "user_course")
public class UserCourseComposite {

	@EmbeddedId
	private UserCourseId id;

	@ManyToOne
	@MapsId(value = "courseId")
	private Course course;

	@ManyToOne
	@MapsId(value = "userId")
	private User user;

	private String semester;
}

@Embeddable
class UserCourseId {

	@JoinColumn(name = "user_id")
	private Long userId;

	@JoinColumn(name = "course_id")
	private Long courseId;
	
//	private Dat....
}
