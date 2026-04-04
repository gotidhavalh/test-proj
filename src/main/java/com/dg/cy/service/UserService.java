package com.dg.cy.service;

import com.dg.cy.dto.UserDTO;
import java.util.List;

public interface UserService {
	List<UserDTO> getAll();
	UserDTO getById(int id);
	UserDTO create(UserDTO dto);
	UserDTO update(int id, UserDTO dto);
	boolean delete(int id);
}
