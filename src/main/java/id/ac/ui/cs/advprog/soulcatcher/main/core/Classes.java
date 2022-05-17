package id.ac.ui.cs.advprog.soulcatcher.main.core;

import javax.persistence.Embeddable;

@Embeddable
public interface Classes {
    void setHp(int value);
    void setDamage(int value);
    void setLevel(int level);
    int getHp();
    int getDamage();
    int getLevel();
}
