package id.ac.ui.cs.advprog.soulcatcher.main.model;

import id.ac.ui.cs.advprog.soulcatcher.main.core.Classes;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="persona")
public class Persona {

    @Id
    @Column(name = "persona_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @Column(name = "class")
//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride( name = "hp", column = @Column(name = "hp")),
//            @AttributeOverride( name = "damage", column = @Column(name = "damage")),
//            @AttributeOverride( name = "level", column = @Column(name = "level"))
//    })
//    private Classes personaClass;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "persona_inventory_name")
    private PersonaInventory personaInventory;

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
