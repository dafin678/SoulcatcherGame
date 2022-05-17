package id.ac.ui.cs.advprog.soulcatcher.main.repository;

import id.ac.ui.cs.advprog.soulcatcher.main.model.PersonaInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaInventoryRepository extends JpaRepository<PersonaInventory, Integer> {
}
