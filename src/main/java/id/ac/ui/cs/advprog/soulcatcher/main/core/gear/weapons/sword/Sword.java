package id.ac.ui.cs.advprog.soulcatcher.main.core.gear.weapons.sword;

import id.ac.ui.cs.advprog.soulcatcher.main.core.gear.weapons.WeaponType;
import id.ac.ui.cs.advprog.soulcatcher.main.core.gear.weapons.WeaponUse;
import id.ac.ui.cs.advprog.soulcatcher.main.model.Weapon;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;



public abstract class Sword implements WeaponUse{

    protected String type;
    protected String name;
    protected int powerDamage;

    public Sword(){
        this.type= WeaponType.SWORD.toString();
    }

    @Override
    public int getPowerDamage() {
        return powerDamage;
    }
    @Override
    public String getWeaponType() {
        return WeaponType.SWORD.toString();
    }

    @Override
    public String getWeaponName() {
        return this.name;
    }

    public void setPowerDamage(int powerDamage) {
        this.powerDamage = powerDamage;
    }

    public void setWeaponName(String name){
        this.name=name;
    }

    abstract String slash();

    @Override
    public String attack() {
        return slash();
    }
}
