package com.dg.cy.controller;

import com.dg.cy.dto.DepartmentDTO;
import com.dg.cy.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

	private final DepartmentService departmentService;

	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@GetMapping
	public List<DepartmentDTO> getAll() {
		return departmentService.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<DepartmentDTO> getById(@PathVariable int id) {
		return departmentService.getById(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@PostMapping
	public ResponseEntity<DepartmentDTO> create(@RequestBody DepartmentDTO dto) {
		DepartmentDTO created = departmentService.create(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	@PutMapping("/{id}")
	public ResponseEntity<DepartmentDTO> update(@PathVariable int id, @RequestBody DepartmentDTO dto) {
		return departmentService.update(id, dto)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {
		boolean deleted = departmentService.delete(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
