package com.dg.cy.service;

import com.dg.cy.model.Employee;
import com.dg.cy.repo.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public List<Employee> getAll() {
		return employeeRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Employee> getById(int id) {
		return employeeRepository.findById(id);
	}

	@Override
	public Employee create(Employee employee) {
		// Ensure a new row is created even if the caller provides an id.
//		employee.setId(null);
		return employeeRepository.save(employee);
	}

	@Override
	public Optional<Employee> update(int id, Employee employee) {
		return employeeRepository.findById(id).map(existing -> {
			existing.setName(employee.getName());
			existing.setAddress(employee.getAddress());
			existing.setSalary(employee.getSalary());
			return employeeRepository.save(existing);
		});
	}

	@Override
	public boolean delete(int id) {
		employeeRepository.deleteById(id);
		return true;
	}
}

