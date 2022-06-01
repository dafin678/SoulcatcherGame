package id.ac.ui.cs.advprog.soulcatcher.authentication.service;

import id.ac.ui.cs.advprog.soulcatcher.authentication.security.JwtUtils;
import id.ac.ui.cs.advprog.soulcatcher.authentication.exception.UserNotFoundException;
import id.ac.ui.cs.advprog.soulcatcher.authentication.model.User;
import id.ac.ui.cs.advprog.soulcatcher.authentication.repository.UserRepository;
import id.ac.ui.cs.advprog.soulcatcher.authentication.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;


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

    private static final String USERNAME_VAR = "username";
    private static final String PASSWORD_VAR = "password";
    private static final String EMAIL_VAR = "email";

    @Override
    public String login(HttpServletRequest loginRequest) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getParameter(USERNAME_VAR),
                        loginRequest.getParameter(PASSWORD_VAR)));
        SecurityContextHolder.getContext().setAuthentication(authentication); // bukan masalah
        return jwtUtils.generateJwtToken(authentication);
    }

    @Override
    public String processForgotPassword(HttpServletRequest forgotPasswordRequest) throws UserNotFoundException, MessagingException {
        String email = forgotPasswordRequest.getParameter(EMAIL_VAR);
        String token = userForgotPasswordService.generateSimpleToken();
        userForgotPasswordService.updateResetPasswordToken(token,email);
        String resetPasswordLink =  Utility.getSiteURL(forgotPasswordRequest) +"/reset_password?token=" + token;
        userForgotPasswordService.sendEmail(email,resetPasswordLink);
        return "sentEmail";
    }

    @Override
    public String processResetPassword(HttpServletRequest forgotPasswordRequest){
        String token = forgotPasswordRequest.getParameter("token");
        String password = forgotPasswordRequest.getParameter(PASSWORD_VAR);
        var user = userForgotPasswordService.getByResetPasswordToken(token);
        if(user == null){
            return "InvalidToken";
        }
        userForgotPasswordService.updatePasswordUser(user,password);
        return "Password changed successfully";
    }

    @Override
    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
        var user = userForgotPasswordService.getByResetPasswordToken(token);
        if(user == null){
            return "InvalidToken";
        }
        model.addAttribute("token",token);
        return "resetPasswordForm";
    }

    @Override
    public String register(HttpServletRequest registerRequest) {
        if (Boolean.TRUE.equals(userRepository.existsByUsername(registerRequest.getParameter(USERNAME_VAR)))) {
            return "UsernameExist";
        }
        if(Boolean.TRUE.equals(userRepository.existsByEmail(registerRequest.getParameter(EMAIL_VAR)))){
            return "EmailExist";
        }
        var user = new User(registerRequest.getParameter(USERNAME_VAR),registerRequest.getParameter(EMAIL_VAR),
                encoder.encode(registerRequest.getParameter(PASSWORD_VAR)));
        userRepository.save(user);
        return "Succesfull";
    }
}
