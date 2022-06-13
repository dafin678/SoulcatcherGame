package id.ac.ui.cs.advprog.soulcatcher.main.core.vo;

import lombok.Data;

@Data
public class CharDetail {
    private int id;
    private int hp;
    private int damage;
    private String name;
    private String state;

    public CharDetail(int id, int hp, int damage, String name) {
        this.id = id;
        this.hp = hp;
        this.damage = damage;
        this.name = name;
        this.state = "alive";
    }
}
