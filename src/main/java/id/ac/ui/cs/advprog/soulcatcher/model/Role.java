package id.ac.ui.cs.advprog.soulcatcher.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(	name = "role")
@Data
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private ERole name;

    public Role(ERole name) {
        this.name = name;
    }
}
