package id.ac.ui.cs.advprog.soulcatcher.main.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.ac.ui.cs.advprog.soulcatcher.model.User;
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

    @JsonIgnore
    @OneToOne(mappedBy = "player")
    private User user;

    public Player(String username, String name) {
        this.username=username;
        this.name=name;
    }
}
