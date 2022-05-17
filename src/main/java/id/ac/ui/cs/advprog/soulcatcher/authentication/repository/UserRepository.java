package id.ac.ui.cs.advprog.soulcatcher.authentication.repository;

import id.ac.ui.cs.advprog.soulcatcher.authentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    @Query("SELECT c FROM User c WHERE c.email = ?1")
    User findByEmail(String email);
    Boolean existsByEmail(String email);
    @Query("SELECT c FROM User c WHERE c.resetPasswordToken = ?1")
    User findByResetPasswordToken(String resetPasswordToken);
}
