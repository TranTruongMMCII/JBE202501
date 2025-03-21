package vn.edu.r2s.jbe202501.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import vn.edu.r2s.jbe202501.dto.request.EnrollRequestDTO;
import vn.edu.r2s.jbe202501.entity.ClassRoom;
import vn.edu.r2s.jbe202501.repository.ClassRoomRepository;
import vn.edu.r2s.jbe202501.response.SuccessResponse;
import vn.edu.r2s.jbe202501.service.ClassRoomService;

@RestController
@RequestMapping(path = "/class-rooms")
@RequiredArgsConstructor
public class ClassRoomController {

	private final ClassRoomService classRoomService;
	private final ClassRoomRepository classRoomRepository;

	@PostMapping(path = "")
	public SuccessResponse<Boolean> enroll(@RequestBody EnrollRequestDTO dto) {
		return SuccessResponse.of(this.classRoomService.enroll(dto.getUserId(), dto.getClassRoomId()));
	}

	@GetMapping(path = "")
	public SuccessResponse<List<ClassRoom>> getAll() {
		List<ClassRoom> classRooms = this.classRoomRepository.findAll();
		System.err.println(classRooms.get(0).getUsers());;
		
		return SuccessResponse.of(classRooms);
	}
}
