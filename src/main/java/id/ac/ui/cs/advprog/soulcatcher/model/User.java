package id.ac.ui.cs.advprog.soulcatcher.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Entity
@Table(	name = "users")
@Data
@NoArgsConstructor
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", updatable = false, nullable = false)
        private Long id;

        @Column(name = "username")
        private String username;

        @Column(name = "password")
        private String password;

        public User(String username, String password) {
                this.username = username;
                this.password = password;
        }
}
