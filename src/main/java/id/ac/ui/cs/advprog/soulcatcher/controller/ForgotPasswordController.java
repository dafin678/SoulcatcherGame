package id.ac.ui.cs.advprog.soulcatcher.controller;


import id.ac.ui.cs.advprog.soulcatcher.exception.UserNotFoundException;
import id.ac.ui.cs.advprog.soulcatcher.model.User;
import id.ac.ui.cs.advprog.soulcatcher.payload.ForgotPasswordRequest;
import id.ac.ui.cs.advprog.soulcatcher.payload.MessageResponse;
import id.ac.ui.cs.advprog.soulcatcher.payload.RegisterRequest;
import id.ac.ui.cs.advprog.soulcatcher.service.UserForgotPasswordService;
import id.ac.ui.cs.advprog.soulcatcher.utility.Utility;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
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
}