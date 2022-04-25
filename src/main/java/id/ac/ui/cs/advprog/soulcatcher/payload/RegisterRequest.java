package id.ac.ui.cs.advprog.soulcatcher.payload;



import javax.validation.constraints.*;

public class RegisterRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotBlank
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail(){ return email; }

    public void setPassword(String password) {
        this.password = password;
    }
}
