package id.ac.ui.cs.advprog.soulcatcher.main.core.persona;

import id.ac.ui.cs.advprog.soulcatcher.main.core.persona.Classes;

public class Knight implements Classes {
    int hp;
    int damage;
    int level;

    public Knight() {
        this.hp = 200;
        this.damage = 50;
        this.level = 1;
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setHp(int value) {
        this.hp = value;
    }

    @Override
    public void setDamage(int value) {
        this.damage = value;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }
}
