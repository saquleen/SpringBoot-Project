package SpringBoot.Kickstart;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	static Optional<User> findByUserName(String userName) {
		return null;
	}
}