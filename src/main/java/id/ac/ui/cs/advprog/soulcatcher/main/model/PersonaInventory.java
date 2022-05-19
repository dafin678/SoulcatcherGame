package id.ac.ui.cs.advprog.soulcatcher.main.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="persona_inventory")
public class PersonaInventory {
    @Id
    @Column(name = "persona_inventory_name")
    @NonNull
    private String name;

    @OneToOne(mappedBy = "personaInventory")
    private Player player;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name="persona_list",
            joinColumns = @JoinColumn(name = "inventory_name"),
            inverseJoinColumns = @JoinColumn(name = "persona_id")
    )
    private List<Persona> personaList = new ArrayList<>();

    public PersonaInventory(String name) {
        this.name=name;
    }


}
