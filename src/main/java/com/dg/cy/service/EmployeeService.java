package com.dg.cy.service;

import com.dg.cy.dto.EmployeeDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
	List<EmployeeDTO> getAll();

	EmployeeDTO getById(int id);

	EmployeeDTO create(EmployeeDTO dto);

	EmployeeDTO update(int id, EmployeeDTO dto);

	boolean delete(int id);
}

