package id.ac.ui.cs.advprog.soulcatcher.controller;

import id.ac.ui.cs.advprog.soulcatcher.exception.UserNotFoundException;
import id.ac.ui.cs.advprog.soulcatcher.model.User;
import id.ac.ui.cs.advprog.soulcatcher.payload.ForgotPasswordRequest;
import id.ac.ui.cs.advprog.soulcatcher.payload.LoginRequest;
import id.ac.ui.cs.advprog.soulcatcher.payload.MessageResponse;
import id.ac.ui.cs.advprog.soulcatcher.payload.RegisterRequest;
import id.ac.ui.cs.advprog.soulcatcher.service.AuthenticationService;
import id.ac.ui.cs.advprog.soulcatcher.service.UserForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class LoginController {
    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    private UserForgotPasswordService userForgotPasswordService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") LoginRequest loginRequest, HttpServletResponse response, Model model) {
        String jwt;
        try {
            jwt = authenticationService.login(loginRequest);
        } catch (Exception e) {
            model.addAttribute("message", "Username atau password yang anda masukkan salah");
            return "login";
        }
        response.addCookie(new Cookie("jwttoken", jwt));
        return "redirect:/dashboard";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") RegisterRequest registerRequest, Model model) {
        String response = authenticationService.register(registerRequest);
        if(response.equals("UsernameExist")) {
            model.addAttribute("message", "Username sudah dipakai");
            return "register";
        }
        if(response.equals("EmailExist")) {
            model.addAttribute("message", "Email sudah dipakai");
            return "register";
        }
        return "redirect:/login";
    }

    @GetMapping("/forgot_password")
    public String showForgotPasswordForm(){
        return "";
    }

    @PostMapping("/forgot_password")
    public ResponseEntity<?> processForgotPassword(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest) throws UserNotFoundException, MessagingException {
        String email = forgotPasswordRequest.getEmail();
        String token = userForgotPasswordService.generateSimpleToken();
        userForgotPasswordService.updateResetPasswordToken(token,email);
        String resetPasswordLink =  "http://localhost:8080"+"/reset_password?token=" + token;
        userForgotPasswordService.sendEmail(email,resetPasswordLink);
        return ResponseEntity.ok(new MessageResponse("Password changed successfully with" +
                " first step!"));
    }

    @PostMapping("/reset_password")
    public ResponseEntity<?> processResetPassword(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest){
        String token = forgotPasswordRequest.getToken();
        String password = forgotPasswordRequest.getPassword();
        User user = userForgotPasswordService.getByResetPasswordToken(token);
        if(user == null){
            return ResponseEntity.badRequest().body(new MessageResponse("Invalid Token!"));
        }
        userForgotPasswordService.updatePasswordUser(user,password);
        return ResponseEntity.ok(new MessageResponse("Password changed successfully with" +
                " last step!"));
    }

    @GetMapping("/wrong")
    public String wrong() {
        return "wrong";
    }
}
