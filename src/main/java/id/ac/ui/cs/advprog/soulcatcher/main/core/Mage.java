package id.ac.ui.cs.advprog.soulcatcher.main.core;

public class Mage extends Character {
    public Mage() {
        super();
        this.setHp(150);
        this.setDamage(30);
    }

    public Mage(int hp, int damage, int level){
        super(hp,damage,level);
    }

    @Override
    public void attack() {
        // untuk implementasi pve nanti
    }
}
