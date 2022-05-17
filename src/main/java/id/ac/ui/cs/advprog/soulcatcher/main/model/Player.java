package id.ac.ui.cs.advprog.soulcatcher.main.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import id.ac.ui.cs.advprog.soulcatcher.authentication.model.User;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="player")
public class Player {
    @Id
    @Column(name = "username")
    @NonNull
    private String username;

    @Column(name = "name")
    @NonNull
    private String name;

    @OneToOne
    @JoinColumn(name = "player_inventory", referencedColumnName ="inventory_name")
    private Inventory playerInventory;

    @OneToOne(mappedBy = "player")
    private User user;

    @OneToOne
    @JoinColumn(name = "persona_inventory", referencedColumnName ="persona_inventory_name")
    private PersonaInventory personaInventory;

    public Player(String username, String name) {
        this.username=username;
        this.name=name;
    }

}
