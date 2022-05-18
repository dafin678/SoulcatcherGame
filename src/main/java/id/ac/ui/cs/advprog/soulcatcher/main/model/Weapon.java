package id.ac.ui.cs.advprog.soulcatcher.main.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import javax.persistence.*;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="weapon")
public class Weapon {
    @Id
    @Column(name = "weapon_name")
    @NonNull
    private String weaponName;


    @Column(name="weapon_type")
    private String weaponType;

    @Column(name="weapon_damage")
    private int weaponDamage;

    @JsonIgnore
    @ManyToMany(mappedBy = "weaponList")
    private List<Inventory> inventories;

    public Weapon(String name,String weaponType, int weaponDamage){
        this.weaponName=name;
        this.weaponType=weaponType;
        this.weaponDamage=weaponDamage;
    }
}

