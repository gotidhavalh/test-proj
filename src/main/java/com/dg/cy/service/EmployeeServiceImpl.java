package com.dg.cy.service;

import com.dg.cy.dto.EmployeeDTO;
import com.dg.cy.model.Department;
import com.dg.cy.model.Employee;
import com.dg.cy.repo.DepartmentRepository;
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
	private final DepartmentRepository departmentRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
		this.employeeRepository = employeeRepository;
		this.departmentRepository = departmentRepository;
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
		
		Employee emp = employeeRepository.getById(id);
		emp.setName(dto.getName());
		emp.setAddress(dto.getAddress());
		emp.setSalary(dto.getSalary());
		emp.setDob(dto.getDob());
		emp.setDepartment(resolveDepartment(dto.getDeptId()));
		
		return toDTO(employeeRepository.save(emp));
		
	}

	@Override
	public boolean delete(int id) {
		employeeRepository.deleteById(id);
		return true;
	}

	private EmployeeDTO toDTO(Employee employee) {
		Integer deptId = employee.getDepartment() != null ? employee.getDepartment().getId() : null;
		return new EmployeeDTO(
				employee.getId(),
				employee.getName(),
				employee.getAddress(),
				employee.getSalary(),
				employee.getDob(),
				deptId
		);
	}

	private Employee toEntity(EmployeeDTO dto) {
		Employee employee = new Employee(
				dto.getId(),
				dto.getName(),
				dto.getAddress(),
				dto.getSalary(),
				dto.getDob(),
				resolveDepartment(dto.getDeptId())
		);
		return employee;
	}

	private Department resolveDepartment(Integer deptId) {
		if (deptId == null) {
			return null;
		}
		return departmentRepository.findById(deptId).orElse(null);
	}
}
