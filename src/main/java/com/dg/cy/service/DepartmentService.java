package com.dg.cy.service;

import com.dg.cy.dto.DepartmentDTO;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

	List<DepartmentDTO> getAll();

	Optional<DepartmentDTO> getById(int id);

	DepartmentDTO create(DepartmentDTO dto);

	Optional<DepartmentDTO> update(int id, DepartmentDTO dto);

	boolean delete(int id);
}
