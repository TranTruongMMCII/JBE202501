package vn.edu.r2s.jbe202501.service.impl;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.edu.r2s.jbe202501.entity.ClassRoom;
import vn.edu.r2s.jbe202501.entity.User;
import vn.edu.r2s.jbe202501.exception.NotFoundException;
import vn.edu.r2s.jbe202501.repository.ClassRoomRepository;
import vn.edu.r2s.jbe202501.repository.UserRepository;
import vn.edu.r2s.jbe202501.service.ClassRoomService;

@Service
@RequiredArgsConstructor
public class ClassRoomServiceImpl implements ClassRoomService {
	
	private final UserRepository userRepository;
	private final ClassRoomRepository classRoomRepository;
	
	@Override
	public boolean enroll(Long userId, Long classRoomId) {
		// check user ton tai
		User foundUser = this.userRepository
				.findById(userId)
				.orElseThrow(()-> new NotFoundException("user", "not found id: " + userId));
		
		// check classroom ton tai
		ClassRoom foundClassRoom = this.classRoomRepository
				.findById(classRoomId)
				.orElseThrow(()-> new NotFoundException("classRoom", "not found id: " + classRoomId));

		// 1. insert ben 1
//		foundUser.setClassRoom(foundClassRoom);
//		foundClassRoom.getUsers().add(foundUser);
//		this.classRoomRepository.save(foundClassRoom);
		
		// 2. insert ben nhieu
		foundUser.setClassRoom(foundClassRoom);
		this.userRepository.save(foundUser);
		
		return true;
	}

}
