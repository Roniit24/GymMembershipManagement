package in.sp.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sp.main.entities.User;

public interface UserRepository extends JpaRepository<User,Long>
{

	Optional<User> findByEmail(String email);

}
