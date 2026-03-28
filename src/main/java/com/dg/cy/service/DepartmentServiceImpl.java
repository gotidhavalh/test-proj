package com.dg.cy.service;

import com.dg.cy.dto.DepartmentDTO;
import com.dg.cy.model.Department;
import com.dg.cy.repo.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

	private final DepartmentRepository departmentRepository;

	public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	@Override
	public List<DepartmentDTO> getAll() {
		return departmentRepository.findAll().stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<DepartmentDTO> getById(int id) {
		return departmentRepository.findById(id).map(this::toDTO);
	}

	@Override
	public DepartmentDTO create(DepartmentDTO dto) {
		return toDTO(departmentRepository.save(toEntity(dto)));
	}

	@Override
	public Optional<DepartmentDTO> update(int id, DepartmentDTO dto) {
		return departmentRepository.findById(id).map(existing -> {
			existing.setName(dto.getName());
			existing.setStartDate(dto.getStartDate());
			existing.setEndDate(dto.getEndDate());
			existing.setIsActive(dto.getIsActive());
			return toDTO(departmentRepository.save(existing));
		});
	}

	@Override
	public boolean delete(int id) {
		departmentRepository.deleteById(id);
		return true;
	}

	private DepartmentDTO toDTO(Department department) {
		return new DepartmentDTO(
				department.getId(),
				department.getName(),
				department.getStartDate(),
				department.getEndDate(),
				department.getIsActive()
		);
	}

	private Department toEntity(DepartmentDTO dto) {
		return new Department(
				dto.getId(),
				dto.getName(),
				dto.getStartDate(),
				dto.getEndDate(),
				dto.getIsActive()
		);
	}
}
