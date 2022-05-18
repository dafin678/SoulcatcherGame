package id.ac.ui.cs.advprog.soulcatcher.main.core;

import id.ac.ui.cs.advprog.soulcatcher.main.core.persona.Classes;

import javax.persistence.Embeddable;

@Embeddable
public abstract class Character implements Classes {
    protected int hp;
    protected int damage;
    protected int level;

    protected Character() {
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

    public void upgrade(int value){
        setHp(getHp() + (int) 0.15 * getHp());
        setDamage(getDamage() +  (int) 0.15 * getHp());
        setLevel(getLevel() + 1);
    }
}
