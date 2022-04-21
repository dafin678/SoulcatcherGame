package id.ac.ui.cs.advprog.soulcatcher.repository;

import id.ac.ui.cs.advprog.soulcatcher.model.ERole;
import id.ac.ui.cs.advprog.soulcatcher.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
