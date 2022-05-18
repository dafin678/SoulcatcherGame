package id.ac.ui.cs.advprog.soulcatcher.main.core.gear.weapons.sword;

import id.ac.ui.cs.advprog.soulcatcher.main.core.gear.weapons.WeaponType;
import id.ac.ui.cs.advprog.soulcatcher.main.model.Weapon;

public class SimitarSword extends Sword {

    public SimitarSword(){
        this.setPowerDamage(50);
        this.setWeaponName("Simitar");
    }

    @Override
    public String slash() {
        return "Simitar sword slash your body";
    }


}
