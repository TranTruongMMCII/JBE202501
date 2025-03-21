package vn.edu.r2s.jbe202501.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import vn.edu.r2s.jbe202501.dto.request.UserRequestDTO;
import vn.edu.r2s.jbe202501.dto.response.UserResponseDTO;
import vn.edu.r2s.jbe202501.entity.User;
import vn.edu.r2s.jbe202501.mapper.UserMapper;
import vn.edu.r2s.jbe202501.response.SuccessResponse;
import vn.edu.r2s.jbe202501.service.UserService;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final UserMapper userMapper;

//	@RequestMapping(method = RequestMethod.GET, path = "/hello") // GET /users/hello
	@PreAuthorize(value = "hasAuthority('ADMIN')")
	@GetMapping(path = "/hello")
	@Operation(summary = "Get a hello by its server")
	public String hello() {

		return "Hello, World!";
	}

	/**
	 * This method get all stored user in db
	 * 
	 * @return list found user
	 */
	@GetMapping(path = "")
	@Deprecated(since = "21.03.2025")
	public SuccessResponse<List<User>> getUsers(Pageable pageable) {
		return SuccessResponse.of(this.userService.getAll(pageable));
	}

	@GetMapping(path = "/searchBy")
	public SuccessResponse<List<User>> getUserByName(
			@RequestParam(name = "name") String name,
			@RequestParam(name = "size", defaultValue = "20") int size, 
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "sorts", required = false) String sorts) {
		PageRequest pageRequest = PageRequest.of(page, size);

		if (Strings.isNotBlank(sorts)) {
			List<Order> orders = new ArrayList<>();
			String[] sortFields = sorts.split(",");
			for (int i = 0; i < sortFields.length; i += 2) {
				switch (sortFields[i + 1]) {
				case "ASC":
					orders.add(Order.asc(sortFields[i]));
					break;
				case "DESC":
					orders.add(Order.desc(sortFields[i]));
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + sortFields[i + 1]);
				}
			}

			pageRequest = PageRequest.of(page, size, Sort.by(orders));
		}
		
		return SuccessResponse.of(this.userService.findByName(name, pageRequest));
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@PreAuthorize(value = "hasRole('ADMIN')")
	@GetMapping(path = "/findByPathVariable/{id}")
	public SuccessResponse<UserResponseDTO> getUserByPathVariableId(@PathVariable(name = "id", required = false) Long id) {
		return SuccessResponse.of(this.userMapper.convertToDTO(this.userService.getById(id)));
	}

	@GetMapping(path = "/findByRequestParam")
	public User getUserByRequestParam(@RequestParam(name = "id", defaultValue = "1") Long id) {
		return this.userService.getById(id);
	}

	@PostMapping(path = "")
	public SuccessResponse<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO dto) {
		User user = this.userMapper.convertToModel(dto);
		
		return SuccessResponse.of(this.userMapper.convertToDTO(this.userService.insertUser(user)));
	}

	@PutMapping(path = "/updateByPathVariable/{id}")
	public User updateUserByPathVariable(@PathVariable(name = "id") Long id, @RequestBody User newUser) {
		return this.userService.updateUser(id, newUser);
	}
	
	@DeleteMapping(path = "/{id}")
	public Boolean deleteUser(@PathVariable(name = "id") Long id) {
		return this.userService.deleteUser(id);
	}
}
