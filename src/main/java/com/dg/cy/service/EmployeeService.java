package com.dg.cy.service;

import com.dg.cy.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
	List<Employee> getAll();

	Optional<Employee> getById(int id);

	Employee create(Employee employee);

	Optional<Employee> update(int id, Employee employee);

	boolean delete(int id);
}

