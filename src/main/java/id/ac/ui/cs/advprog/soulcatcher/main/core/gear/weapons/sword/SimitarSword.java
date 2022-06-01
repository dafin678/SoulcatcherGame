package id.ac.ui.cs.advprog.soulcatcher.main.core.gear.weapons.sword;


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
