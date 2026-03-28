package com.dg.cy.controller;

import com.dg.cy.dto.EmployeeDTO;
import com.dg.cy.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping
	public List<EmployeeDTO> getAll() {
		return employeeService.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDTO> getById(@PathVariable int id) {
		return employeeService.getById(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@PostMapping
	public ResponseEntity<EmployeeDTO> create(@RequestBody EmployeeDTO dto) {
		EmployeeDTO created = employeeService.create(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDTO> update(@PathVariable int id, @RequestBody EmployeeDTO dto) {
		return employeeService.update(id, dto)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {
		boolean deleted = employeeService.delete(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}

