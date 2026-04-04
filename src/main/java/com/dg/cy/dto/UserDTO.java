package com.dg.cy.dto;

public class UserDTO {

	private Integer id;
	private String name;
	private String email;
	private Boolean isActive;

	public UserDTO() {}

	public UserDTO(Integer id, String name, String email, Boolean isActive) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.isActive = isActive;
	}

	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	public Boolean getIsActive() { return isActive; }
	public void setIsActive(Boolean isActive) { this.isActive = isActive; }
}
