package id.ac.ui.cs.advprog.soulcatcher.controller;

import id.ac.ui.cs.advprog.soulcatcher.exception.UserNotFoundException;
import id.ac.ui.cs.advprog.soulcatcher.payload.ForgotPasswordRequest;
import id.ac.ui.cs.advprog.soulcatcher.payload.LoginRequest;
import id.ac.ui.cs.advprog.soulcatcher.payload.RegisterRequest;
import id.ac.ui.cs.advprog.soulcatcher.service.AuthenticationService;
import id.ac.ui.cs.advprog.soulcatcher.service.UserForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;

@Controller
public class LoginController {
    @Autowired
    AuthenticationService authenticationService;


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") LoginRequest loginRequest) {
        try {
            authenticationService.login(loginRequest);
        } catch (Exception e) {
            return "redirect:/wrong";
        }
        return "redirect:/dashboard";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") RegisterRequest registerRequest) {
        String response = authenticationService.register(registerRequest);
        if(response.equals("UsernameExist")) {
            return "redirect:/wrong";
        }
        if(response.equals("EmailExist")) {
            return "redirect:/wrong";
        }
        return "redirect:/login";
    }

    @GetMapping("/forgot_password")
    public String showForgotPasswordForm(){
        return "forgotPass";
    }
    @PostMapping("/forgot_password")
    public String processForgotPassword(@ModelAttribute("user")  ForgotPasswordRequest forgotPasswordRequest) throws UserNotFoundException, MessagingException {
        String response = authenticationService.processForgotPassword(forgotPasswordRequest);
        System.out.println(response);
        if(response.equals("Link to reset your password has been sent to your email")) {
            return "redirect:/login";
        }
        return "redirect:/forgotPass";
    }

    @GetMapping("/reset_password")
    public String showResetPasswordForm(){
        return "changePass";
    }

    @PostMapping("/reset_password")
    public String processResetPassword(ForgotPasswordRequest forgotPasswordRequest){
        String response = authenticationService.processResetPassword(forgotPasswordRequest);
        if(response.equals("Invalid Token!")){
            return "redirect:/forgotPass";
        }
        else if(response.equals("Password changed successfully")){
            return "redirect:/login";
        }
        return "redirect:/forgotPass";
    }

    @GetMapping("/dummy")
    public String dummy() {
        return "dummy";
    }

    @GetMapping("/wrong")
    public String wrong() {
        return "wrong";
    }
}
