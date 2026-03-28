package com.dg.cy.service;

import com.dg.cy.dto.EmployeeDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
	List<EmployeeDTO> getAll();

	Optional<EmployeeDTO> getById(int id);

	EmployeeDTO create(EmployeeDTO dto);

	Optional<EmployeeDTO> update(int id, EmployeeDTO dto);

	boolean delete(int id);
}

