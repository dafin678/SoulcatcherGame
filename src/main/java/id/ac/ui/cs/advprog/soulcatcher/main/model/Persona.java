package id.ac.ui.cs.advprog.soulcatcher.main.model;

import id.ac.ui.cs.advprog.soulcatcher.authentication.model.User;
import id.ac.ui.cs.advprog.soulcatcher.main.core.persona.Classes;
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

    @Column(name = "class")
    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride( name = "hp", column = @Column(name = "hp")),
//            @AttributeOverride( name = "damage", column = @Column(name = "damage")),
//            @AttributeOverride( name = "level", column = @Column(name = "level"))
//    })
    private Classes persona_class;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "persona_inventory_name")
    private PersonaInventory personaInventory;


    public Persona(Classes persona_class, String name) {
        this.persona_class = persona_class;
        this.name = name;
    }
}
