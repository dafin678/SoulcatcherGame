package id.ac.ui.cs.advprog.soulcatcher.main.core;

public class Priest extends Character{
    public Priest() {
        super();
        this.setHp(100);
        this.setDamage(20);
    }

    public Priest(int hp, int damage, int level){
        super(hp,damage,level);
    }

    @Override
    public void attack() {
        // untuk implementasi pve nanti
    }

}
