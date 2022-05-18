package id.ac.ui.cs.advprog.soulcatcher.main.core.gear.weapons.sword;



public class NapoleonSword extends Sword{


    public NapoleonSword(){
        this.setPowerDamage(35);
        this.setWeaponName("Napoleon");
    }

    @Override
    public String slash() {
        return "Napoleon sword slash your body";
    }

}
