package id.ac.ui.cs.advprog.soulcatcher.main.core.persona;

import id.ac.ui.cs.advprog.soulcatcher.main.core.Character;

public class Mage extends Character {
    public Mage() {
        super();
        this.setHp(150);
        this.setDamage(30);
    }

    @Override
    public void attack() {
        // untuk implementasi pve nanti
    }

    @Override
    public void upgrade() {

    }
}
