package com.dg.cy.service;

import com.dg.cy.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
	List<UserDTO> getAll();
	Optional<UserDTO> getById(int id);
	UserDTO create(UserDTO dto);
	Optional<UserDTO> update(int id, UserDTO dto);
	boolean delete(int id);
}
