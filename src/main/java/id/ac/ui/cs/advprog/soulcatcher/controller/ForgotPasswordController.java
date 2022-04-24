package id.ac.ui.cs.advprog.soulcatcher.controller;


import id.ac.ui.cs.advprog.soulcatcher.exception.UserNotFoundException;
import id.ac.ui.cs.advprog.soulcatcher.payload.ForgotPasswordRequest;
import id.ac.ui.cs.advprog.soulcatcher.payload.MessageResponse;
import id.ac.ui.cs.advprog.soulcatcher.payload.RegisterRequest;
import id.ac.ui.cs.advprog.soulcatcher.service.UserDetailsImpl;
import id.ac.ui.cs.advprog.soulcatcher.service.UserDetailsService;
import id.ac.ui.cs.advprog.soulcatcher.service.UserDetailsServiceImpl;
import id.ac.ui.cs.advprog.soulcatcher.service.UserForgotPasswordService;
import id.ac.ui.cs.advprog.soulcatcher.utility.Utility;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@Controller
public class ForgotPasswordController {

    @Autowired
    private UserForgotPasswordService userForgotPasswordService;

    @GetMapping("/forgot_password")
    public String showForgotPasswordForm(){
        return "";
    }
    @PostMapping("/forgot_password")
    public ResponseEntity<?> processForgotPassword(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest) throws UserNotFoundException, MessagingException{
        String email = forgotPasswordRequest.getEmail();
        String token = RandomString.make(45);
        userForgotPasswordService.updateResetPasswordToken(token,email);

        String resetPasswordLink = Utility.getSiteURL(forgotPasswordRequest) + "/reset_password?token=" + token;

        userForgotPasswordService.sendEmail(email,resetPasswordLink);

        return ResponseEntity.ok(new MessageResponse("Password changed successfully!"));

    }
}