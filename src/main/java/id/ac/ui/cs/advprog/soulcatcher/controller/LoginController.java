package id.ac.ui.cs.advprog.soulcatcher.controller;

import id.ac.ui.cs.advprog.soulcatcher.payload.LoginRequest;
import id.ac.ui.cs.advprog.soulcatcher.payload.RegisterRequest;
import id.ac.ui.cs.advprog.soulcatcher.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
        return "redirect:/dummy";
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

    @GetMapping("/dummy")
    public String dummy() {
        return "dummy";
    }

    @GetMapping("/wrong")
    public String wrong() {
        return "wrong";
    }
}
