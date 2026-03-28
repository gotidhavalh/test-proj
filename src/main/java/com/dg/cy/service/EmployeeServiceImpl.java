package com.dg.cy.service;

import com.dg.cy.dto.EmployeeDTO;
import com.dg.cy.model.Employee;
import com.dg.cy.repo.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public List<EmployeeDTO> getAll() {
		return employeeRepository.findAll().stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<EmployeeDTO> getById(int id) {
		return employeeRepository.findById(id).map(this::toDTO);
	}

	@Override
	public EmployeeDTO create(EmployeeDTO dto) {
		return toDTO(employeeRepository.save(toEntity(dto)));
	}

	@Override
	public Optional<EmployeeDTO> update(int id, EmployeeDTO dto) {
		return employeeRepository.findById(id).map(existing -> {
			existing.setName(dto.getName());
			existing.setAddress(dto.getAddress());
			existing.setSalary(dto.getSalary());
			existing.setDob(dto.getDob());
			return toDTO(employeeRepository.save(existing));
		});
	}

	@Override
	public boolean delete(int id) {
		employeeRepository.deleteById(id);
		return true;
	}

	private EmployeeDTO toDTO(Employee employee) {
		return new EmployeeDTO(
				employee.getId(),
				employee.getName(),
				employee.getAddress(),
				employee.getSalary(),
				employee.getDob()
		);
	}

	private Employee toEntity(EmployeeDTO dto) {
		return new Employee(
				dto.getId(),
				dto.getName(),
				dto.getAddress(),
				dto.getSalary(),
				dto.getDob()
		);
	}
}

