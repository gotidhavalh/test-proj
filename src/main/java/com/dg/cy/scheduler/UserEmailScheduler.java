package com.dg.cy.scheduler;

import com.dg.cy.model.User;
import com.dg.cy.repo.UserRepository;
import com.dg.cy.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserEmailScheduler {

	private static final Logger log = LoggerFactory.getLogger(UserEmailScheduler.class);

	private final UserRepository userRepository;
	private final EmailService emailService;

	public UserEmailScheduler(UserRepository userRepository, EmailService emailService) {
		this.userRepository = userRepository;
		this.emailService = emailService;
	}

	@Scheduled(cron = "0 0 2 * * *")
	public void sendDailyEmailToAllUsers() {
		List<User> users = userRepository.findAll().stream()
				.filter(u -> Boolean.TRUE.equals(u.getIsActive()))
				.collect(Collectors.toList());

		log.info("Sending daily email to {} active users", users.size());

		for (User user : users) {
			try {
				emailService.sendEmail(
						user.getEmail(),
						"Daily Update",
						"Hello " + user.getName() + ",\n\nThis is your daily update.\n\nRegards,\ndg-test-app"
				);
				log.info("Email sent to {}", user.getEmail());
			} catch (Exception e) {
				log.error("Failed to send email to {}: {}", user.getEmail(), e.getMessage());
			}
		}
	}
}
