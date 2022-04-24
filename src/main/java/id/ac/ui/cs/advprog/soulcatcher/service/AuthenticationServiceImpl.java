package id.ac.ui.cs.advprog.soulcatcher.service;

import id.ac.ui.cs.advprog.soulcatcher.Security.JwtUtils;
import id.ac.ui.cs.advprog.soulcatcher.exception.UserNotFoundException;
import id.ac.ui.cs.advprog.soulcatcher.model.User;
import id.ac.ui.cs.advprog.soulcatcher.payload.ForgotPasswordRequest;
import id.ac.ui.cs.advprog.soulcatcher.payload.LoginRequest;
import id.ac.ui.cs.advprog.soulcatcher.payload.MessageResponse;
import id.ac.ui.cs.advprog.soulcatcher.payload.RegisterRequest;
import id.ac.ui.cs.advprog.soulcatcher.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.MessagingException;
import javax.validation.Valid;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private UserForgotPasswordService userForgotPasswordService;

    @Override
    public String login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        return jwt;
    }

    @Override
    public String processForgotPassword( ForgotPasswordRequest forgotPasswordRequest) throws UserNotFoundException, MessagingException {
        String email = forgotPasswordRequest.getEmail();
        String token = userForgotPasswordService.generateSimpleToken();
        userForgotPasswordService.updateResetPasswordToken(token,email);
        String resetPasswordLink =  "http://localhost:8080"+"/reset_password?token=" + token;
        userForgotPasswordService.sendEmail(email,resetPasswordLink);
        return "Link to reset your password has been sent to your email";
    }

    @Override
    public String processResetPassword( ForgotPasswordRequest forgotPasswordRequest){
        String token = forgotPasswordRequest.getToken();
        String password = forgotPasswordRequest.getPassword();
        User user = userForgotPasswordService.getByResetPasswordToken(token);
        if(user == null){
            return "Invalid Token!";
        }
        userForgotPasswordService.updatePasswordUser(user,password);
        return "Password changed successfully";
    }

    @Override
    public String register(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return "UsernameExist";
        }
        if(userRepository.existsByEmail(registerRequest.getEmail())){
            return "EmailExist";
        }
        User user = new User(registerRequest.getUsername(),registerRequest.getEmail(),
                encoder.encode(registerRequest.getPassword()));
        userRepository.save(user);

        return "Succesfull";
    }
}
