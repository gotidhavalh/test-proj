package com.dg.cy.dto;

import java.time.LocalDate;

public class EmployeeDTO {

	private Integer id;

	private String name;

	private String address;

	private Double salary;

	private LocalDate dob;

	private Integer deptId;

	public EmployeeDTO() {
	}

	public EmployeeDTO(Integer id, String name, String address, Double salary, LocalDate dob, Integer deptId) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.salary = salary;
		this.dob = dob;
		this.deptId = deptId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
}
