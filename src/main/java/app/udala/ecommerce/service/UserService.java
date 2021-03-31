package app.udala.ecommerce.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.udala.ecommerce.controller.advice.DuplicatedEntryException;
import app.udala.ecommerce.model.User;
import app.udala.ecommerce.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public User save(User user) throws DuplicatedEntryException {
		Optional<User> checkDuplicates = userRepository.findByUniqueKeys(user);
		
		if (checkDuplicates.isPresent()) {
			throw new DuplicatedEntryException(user, checkDuplicates.get());
		}

		return userRepository.save(user);
	}
	
	public Optional<User> get(String publicId) {
		try {
			return userRepository.findByPublicId(UUID.fromString(publicId));			
		} catch(IllegalArgumentException e) {
			return Optional.empty();
		}
	}
}
