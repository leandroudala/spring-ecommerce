package app.udala.ecommerce.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.udala.ecommerce.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByPublicId(UUID publicId);

	Optional<User> findByEmail(String email);

	@Query("SELECT u FROM User u WHERE u.cpf = :#{#user.cpf} or u.email = :#{#user.email} or u.phone = :#{#user.phone}")
	Optional<User> findByUniqueKeys(@Param("user") User user);
	
}
