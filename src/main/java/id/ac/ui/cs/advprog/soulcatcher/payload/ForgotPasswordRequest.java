package id.ac.ui.cs.advprog.soulcatcher.payload;

import javax.validation.constraints.NotBlank;


public class ForgotPasswordRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String token;

    @NotBlank
    private String password;

    public String getEmail(){ return email;}
    public String getToken(){ return token;}
    public String getPassword(){ return password;}

}
