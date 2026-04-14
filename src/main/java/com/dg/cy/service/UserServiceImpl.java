package com.dg.cy.service;

import com.dg.cy.dto.UserDTO;
import com.dg.cy.model.User;
import com.dg.cy.repo.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<UserDTO> getAll() {
		return userRepository.findAll().stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public UserDTO getById(int id) {
		// Use a real lookup so missing users return a 404 instead of a lazy proxy failure.
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
		return toDTO(user);
	}

	@Override
	public UserDTO create(UserDTO dto) {
		return toDTO(userRepository.save(toEntity(dto)));
	}

	@Override
	public UserDTO update(int id, UserDTO dto) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setIsActive(dto.getIsActive());
		return toDTO(userRepository.save(user));
	}

	@Override
	public boolean delete(int id) {
		if (!userRepository.existsById(id)) {
			return false;
		}
		userRepository.deleteById(id);
		return true;
	}

	private UserDTO toDTO(User user) {
		return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getIsActive());
	}

	private User toEntity(UserDTO dto) {
		return new User(dto.getId(), dto.getName(), dto.getEmail(), dto.getIsActive());
	}
}
