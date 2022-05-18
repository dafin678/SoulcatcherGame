package id.ac.ui.cs.advprog.soulcatcher.main.repository;

import id.ac.ui.cs.advprog.soulcatcher.main.model.Player;
import id.ac.ui.cs.advprog.soulcatcher.main.model.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeaponRepository extends JpaRepository<Weapon, Integer> {
    Weapon findWeaponByWeaponName(String name);
}
