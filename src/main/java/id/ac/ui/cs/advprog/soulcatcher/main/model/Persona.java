package id.ac.ui.cs.advprog.soulcatcher.main.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="persona")
public class Persona {

    @Id
    @Column(name = "persona_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "persona_name")
    private String name;

    @Column(name = "persona_hp")
    private int hp;

    @Column(name = "persona_damage")
    private int damage;

    @Column(name = "persona_level")
    private int level;

    @Column(name = "persona_class")
    private String personaClass;

    @ManyToMany(mappedBy = "personaList")
    private List<PersonaInventory> personaInventory;

    @Column
    private int soulFragment = 0;


    public Persona(String name, int hp, int damage, int level, String personaClass) {
        this.name = name;
        this.hp = hp;
        this.damage = damage;
        this.level = level;
        this.personaClass = personaClass;
    }
}
