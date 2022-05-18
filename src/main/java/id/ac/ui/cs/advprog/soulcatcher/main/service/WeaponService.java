package id.ac.ui.cs.advprog.soulcatcher.main.service;

import id.ac.ui.cs.advprog.soulcatcher.main.model.Consumable;
import id.ac.ui.cs.advprog.soulcatcher.main.model.Weapon;

import java.util.List;

public interface WeaponService {
    Weapon createWeapon(String name,String type);
    List<Weapon> getWeapon();
}

