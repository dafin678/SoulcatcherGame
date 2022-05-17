package id.ac.ui.cs.advprog.soulcatcher.main.core;

import javax.persistence.Embeddable;

@Embeddable
public abstract class Character implements Classes {
    protected int hp;
    protected int damage;
    protected int level;

//    public Classes(int hp, int damage, int level) {
//        this.hp = hp;
//        this.damage = damage;
//        this.level = level;
//    }

    public Character() {
        this.setLevel(1);
    }

    public void setHp(int value) {
        this.hp = value;
    }

    public void setDamage(int value) {
        this.damage = value;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHp() {
        return this.hp;
    }

    public int getDamage() {
        return this.damage;
    }

    public int getLevel() {
        return this.level;
    }

    public abstract void attack();
}
