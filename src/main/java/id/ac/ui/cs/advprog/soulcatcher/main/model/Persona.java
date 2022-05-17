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

    @Column(name = "class")
    @Embedded
    private Classes personaClass;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "persona_inventory_name")
    private PersonaInventory personaInventory;

    public Persona(Classes personaClass, String name) {
        this.personaClass = personaClass;
        this.name = name;
    }
}
