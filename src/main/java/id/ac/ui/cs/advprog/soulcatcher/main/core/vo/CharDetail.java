package id.ac.ui.cs.advprog.soulcatcher.main.core.vo;

import lombok.Data;

@Data
public class CharDetail {
    private int hp;
    private int damage;
    private String name;
    private String type;

    public CharDetail(int hp, int damage, String name, String type) {
        this.hp = hp;
        this.damage = damage;
        this.name = name;
        this.type = type;
    }
}
