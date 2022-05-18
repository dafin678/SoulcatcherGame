package id.ac.ui.cs.advprog.soulcatcher.main.service;

import id.ac.ui.cs.advprog.soulcatcher.main.core.factory.weaponfactory.SwordFactory;
import id.ac.ui.cs.advprog.soulcatcher.main.core.gear.weapons.WeaponType;
import id.ac.ui.cs.advprog.soulcatcher.main.core.gear.weapons.WeaponUse;
import id.ac.ui.cs.advprog.soulcatcher.main.model.Weapon;
import id.ac.ui.cs.advprog.soulcatcher.main.repository.WeaponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeaponServiceImpl implements WeaponService{

    @Autowired
    WeaponRepository weaponRepository;

    @Override
    public Weapon createWeapon(String name,String type) {
        Weapon weapon = null;

        if(type.equalsIgnoreCase(WeaponType.SWORD.toString())){
            WeaponUse sword = SwordFactory.createSword(name);
            if(sword!=null){
                weapon = new Weapon(sword.getWeaponName(),WeaponType.SWORD.toString(),sword.getPowerDamage());
            }
            return weaponRepository.save(weapon);
        }
        return  null;
    }

    @Override
    public List<Weapon> getWeapon() {
        return weaponRepository.findAll();
    }
}



