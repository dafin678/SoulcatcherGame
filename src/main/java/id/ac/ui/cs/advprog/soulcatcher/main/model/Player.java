package id.ac.ui.cs.advprog.soulcatcher.main.model;

import id.ac.ui.cs.advprog.soulcatcher.authentication.model.User;
import lombok.*;
import javax.persistence.*;

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

    @Column
    private int soulFragment = 0;

    @OneToOne
    @JoinColumn(name = "persona_inventory", referencedColumnName ="persona_inventory_name")
    private PersonaInventory personaInventory;

    public Player(String username, String name) {
        this.username=username;
        this.name=name;
    }

}
