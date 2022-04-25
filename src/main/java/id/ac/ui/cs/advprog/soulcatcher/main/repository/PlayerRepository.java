package id.ac.ui.cs.advprog.soulcatcher.main.repository;

import id.ac.ui.cs.advprog.soulcatcher.main.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    Player findPlayerByUsername(String username);
}
