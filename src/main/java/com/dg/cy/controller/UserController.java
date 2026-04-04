package com.dg.cy.controller;

import com.dg.cy.dto.UserDTO;
import com.dg.cy.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<UserDTO> getAll() {
		return userService.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getById(@PathVariable int id) {
		return ResponseEntity.ok(userService.getById(id));
	}

	@PostMapping
	public ResponseEntity<UserDTO> create(@RequestBody UserDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable int id, @RequestBody UserDTO dto) {
		return ResponseEntity.ok(userService.update(id, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {
		boolean deleted = userService.delete(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
