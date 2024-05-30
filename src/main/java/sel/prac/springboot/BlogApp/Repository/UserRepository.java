package sel.prac.springboot.BlogApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sel.prac.springboot.BlogApp.Entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
