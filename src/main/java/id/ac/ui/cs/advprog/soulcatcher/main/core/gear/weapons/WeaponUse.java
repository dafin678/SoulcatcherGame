package id.ac.ui.cs.advprog.soulcatcher.main.core.gear.weapons;



public interface WeaponUse {
    String attack();
    int getPowerDamage();
    void setPowerDamage(int powerDamage);
    String getWeaponType();
    String getWeaponName();
    void setWeaponName(String name);

}
