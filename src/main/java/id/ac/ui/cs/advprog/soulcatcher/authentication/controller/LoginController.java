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

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest loginRequest, HttpServletResponse response, Model model) {
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
    public String register(HttpServletRequest registerRequest, Model model) {
        String response = authenticationService.register(registerRequest);
        if (response.equals("UsernameExist")) {
            model.addAttribute("message", "Username sudah dipakai");
            return "register";
        }
        if (response.equals("EmailExist")) {
            model.addAttribute("message", "Email sudah dipakai");
            return "register";
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
                model.addAttribute("message",
                        "Link to reset your password has been sent to your email");
                return "login";
            }
        }catch(Exception ex){
            model.addAttribute("error",ex.getMessage());
            return "forgotPass";
        }
        return "redirect:/forgotPass";
    }

    @GetMapping("/reset_password")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model){
        String response = authenticationService.showResetPasswordForm(token,model);
        if(response.equals("resetPasswordForm")){
            return "changePass";
        }
        model.addAttribute("message","Invalid Token!");
        return "redirect:/forgotPass";
    }

    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest forgotPasswordRequest, Model model){
        String response = authenticationService.processResetPassword(forgotPasswordRequest);
        if(response.equals("InvalidToken")){
            model.addAttribute("message","Invalid Token!");
            return "redirect:/forgotPass";
        }
        else if(response.equals("Password changed successfully")){
            model.addAttribute("message","Password changed successfully");
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
