package id.ac.ui.cs.advprog.soulcatcher.authentication.controller;

import id.ac.ui.cs.advprog.soulcatcher.authentication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    @Autowired
    AuthenticationService authenticationService;

    private static final String LOGIN_VAR = "login";
    private static final String MESSAGE_VAR = "message";
    private static final String REGISTER_VAR = "register";
    private static final String FORGOT_VAR = "redirect:/forgotPass";

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return LOGIN_VAR;
    }

    @PostMapping("/login")
    public String login(HttpServletRequest loginRequest, HttpServletResponse response, Model model) {
        String jwt;
        try {
            jwt = authenticationService.login(loginRequest);
        } catch (Exception e) {
            model.addAttribute(MESSAGE_VAR, "Username atau password yang anda masukkan salah");
            return LOGIN_VAR;
        }
        Cookie c = new Cookie("jwttoken", jwt);
        c.setHttpOnly(true);
        response.addCookie(c);

        return "redirect:/dashboard";
    }

    @GetMapping("/register")
    public String register() {
        return REGISTER_VAR;
    }

    @PostMapping("/register")
    public String register(HttpServletRequest registerRequest, Model model) {
        String response = authenticationService.register(registerRequest);
        if (response.equals("UsernameExist")) {
            model.addAttribute(MESSAGE_VAR, "Username sudah dipakai");
            return REGISTER_VAR;
        }
        if (response.equals("EmailExist")) {
            model.addAttribute(MESSAGE_VAR, "Email sudah dipakai");
            return REGISTER_VAR;
        }
        return "redirect:/login";

    }


    @GetMapping("/forgot_password")
    public String showForgotPasswordForm(){
        return "forgotPass";
    }
    @PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest forgotPasswordRequest, Model model) {

        try {
            String response = authenticationService.processForgotPassword(forgotPasswordRequest);
            if (response.equals("sentEmail")) {
                model.addAttribute(MESSAGE_VAR,
                        "Link to reset your password has been sent to your email");
                return LOGIN_VAR;
            }
        }catch(Exception ex){
            model.addAttribute("error",ex.getMessage());
            return "forgotPass";
        }
        return FORGOT_VAR;
    }

    @GetMapping("/reset_password")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model){
        String response = authenticationService.showResetPasswordForm(token,model);
        if(response.equals("resetPasswordForm")){
            return "changePass";
        }
        model.addAttribute(MESSAGE_VAR,"Invalid Token!");
        return FORGOT_VAR;
    }

    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest forgotPasswordRequest, Model model){
        String response = authenticationService.processResetPassword(forgotPasswordRequest);
        if(response.equals("InvalidToken")){
            model.addAttribute(MESSAGE_VAR,"Invalid Token!");
            return FORGOT_VAR;
        }
        else if(response.equals("Password changed successfully")){
            model.addAttribute(MESSAGE_VAR,"Password changed successfully");
            return "redirect:/login";
        }
        return FORGOT_VAR;
    }
}
