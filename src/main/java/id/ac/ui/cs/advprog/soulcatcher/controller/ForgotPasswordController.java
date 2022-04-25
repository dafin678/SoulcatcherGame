// UNTUK TEST DI POSTMAN


//package id.ac.ui.cs.advprog.soulcatcher.controller;
//import id.ac.ui.cs.advprog.soulcatcher.exception.UserNotFoundException;
//import id.ac.ui.cs.advprog.soulcatcher.model.User;
//import id.ac.ui.cs.advprog.soulcatcher.payload.ForgotPasswordRequest;
//import id.ac.ui.cs.advprog.soulcatcher.payload.MessageResponse;
//import id.ac.ui.cs.advprog.soulcatcher.service.UserForgotPasswordService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.mail.MessagingException;
//import javax.validation.Valid;
//import java.net.http.HttpResponse;
//
//@RestController
//public class ForgotPasswordController {
//
//    @Autowired
//    UserForgotPasswordService userForgotPasswordService;
//
//    @PostMapping("/forgot_password")
//    public ResponseEntity<?> processForgotPassword(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest) throws UserNotFoundException, MessagingException {
//        String email = forgotPasswordRequest.getEmail();
//        String token = userForgotPasswordService.generateSimpleToken();
//        userForgotPasswordService.updateResetPasswordToken(token,email);
//        String resetPasswordLink =  "http://localhost:8080"+"/reset_password?token=" + token;
//        userForgotPasswordService.sendEmail(email,resetPasswordLink);
//        return ResponseEntity.ok(new MessageResponse("Link to reset your password has been sent to your email"));
//    }
//
//    @PostMapping("/reset_password")
//    public ResponseEntity<?> processResetPassword(ForgotPasswordRequest forgotPasswordRequest){
//        String token = forgotPasswordRequest.getToken();
//        String password = forgotPasswordRequest.getPassword();
//        User user = userForgotPasswordService.getByResetPasswordToken(token);
//        if(user == null){
//            return ResponseEntity.badRequest().body(new MessageResponse("Invalid Token!"));
//        }
//        userForgotPasswordService.updatePasswordUser(user,password);
//        return ResponseEntity.ok(new MessageResponse("Password changed successfully"));
//    }
//
//}
