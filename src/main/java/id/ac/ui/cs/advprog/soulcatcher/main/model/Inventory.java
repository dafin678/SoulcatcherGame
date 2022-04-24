package id.ac.ui.cs.advprog.soulcatcher.main.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="inventory")
public class Inventory {
    @Id
    @Column(name = "inventory_name")
    @NonNull
    private String name;

    @ManyToMany
    @JoinTable(
            name="consumable_inventory",
            joinColumns = @JoinColumn(name = "inventory_name"),
            inverseJoinColumns = @JoinColumn(name = "consumable_id")
    )
    private List<Consumable> consumableList = new ArrayList<>();

    @JsonIgnore
    @OneToOne(mappedBy = "playerInventory")
    private Player player;

    public Inventory(String name) {
        this.name=name;
    }
}