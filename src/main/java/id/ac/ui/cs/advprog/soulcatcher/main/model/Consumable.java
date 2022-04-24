package id.ac.ui.cs.advprog.soulcatcher.main.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="consumable")
public class Consumable {

    @Id
    @Column(name="consumable_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="consumable_name")
    private String name;

    @Column(name="consumable_desc")
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "consumableList")
    private List<Inventory> inventories;

    public Consumable(String name, String description) {
        this.name=name;
        this.description=description;
    }
}
