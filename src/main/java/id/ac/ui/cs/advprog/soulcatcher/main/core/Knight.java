package id.ac.ui.cs.advprog.soulcatcher.main.core;

public class Knight extends Character {

    public Knight() {
        super();
        this.setHp(200);
        this.setDamage(50);
    }

    public Knight(int hp, int damage, int level){
        super(hp,damage,level);
    }

    @Override
    public void attack() {
        // untuk implementasi pve nanti
    }
}
