package com.dg.cy.dto;

import java.time.LocalDate;

public class DepartmentDTO {

	private Integer id;

	private String name;

	private LocalDate startDate;

	private LocalDate endDate;

	private Boolean isActive;

	public DepartmentDTO() {
	}

	public DepartmentDTO(Integer id, String name, LocalDate startDate, LocalDate endDate, Boolean isActive) {
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isActive = isActive;
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
