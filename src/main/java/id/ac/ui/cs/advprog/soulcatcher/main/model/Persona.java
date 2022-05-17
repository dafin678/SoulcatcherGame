package id.ac.ui.cs.advprog.soulcatcher.main.model;

import com.fasterxml.jackson.annotation.*;
import id.ac.ui.cs.advprog.soulcatcher.main.core.Classes;
import id.ac.ui.cs.advprog.soulcatcher.main.repository.PersonaInventoryRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ManyToAny;

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
