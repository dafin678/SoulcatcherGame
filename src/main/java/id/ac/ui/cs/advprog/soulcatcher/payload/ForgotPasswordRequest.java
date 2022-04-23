package id.ac.ui.cs.advprog.soulcatcher.payload;

import javax.validation.constraints.NotBlank;


public class ForgotPasswordRequest {

    @NotBlank
    private String password;

    @NotBlank
    private String email;


    public String getPassword() {
        return password;
    }

    public String getEmail(){ return email; }

    public void setPassword(String password) {
        this.password = password;
    }
}
