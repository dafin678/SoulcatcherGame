package id.ac.ui.cs.advprog.soulcatcher.authentication.model;

import id.ac.ui.cs.advprog.soulcatcher.main.model.Player;
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

        @Column(name = "email")
        private String email;

        @Column(name = "password")
        private String password;

        @Column(name = "reset_password_token")
        private String resetPasswordToken;

        @OneToOne
        @JoinColumn(name = "player_user", referencedColumnName ="username")
        private Player player;


        public User(String username,String email,String password) {
                this.username = username;
                this.password = password;
                this.email=email;
        }

        public String getResetPasswordToken() {
                return resetPasswordToken;
        }

        public void setResetPasswordToken(String resetPasswordToken) {
                this.resetPasswordToken = resetPasswordToken;
        }
}
