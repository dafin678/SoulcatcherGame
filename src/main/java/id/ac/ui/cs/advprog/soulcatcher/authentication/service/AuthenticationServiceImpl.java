package id.ac.ui.cs.advprog.soulcatcher.authentication.service;

import id.ac.ui.cs.advprog.soulcatcher.authentication.Security.JwtUtils;
import id.ac.ui.cs.advprog.soulcatcher.authentication.exception.UserNotFoundException;
import id.ac.ui.cs.advprog.soulcatcher.authentication.model.User;
import id.ac.ui.cs.advprog.soulcatcher.authentication.repository.UserRepository;
import id.ac.ui.cs.advprog.soulcatcher.authentication.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

    @Override
    public String login(HttpServletRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getParameter("username"),
                        loginRequest.getParameter("password")));
        SecurityContextHolder.getContext().setAuthentication(authentication); // bukan masalah
        String jwt = jwtUtils.generateJwtToken(authentication);
        return jwt;
    }

    @Override
    public String processForgotPassword(HttpServletRequest forgotPasswordRequest) throws UserNotFoundException, MessagingException {
        String email = forgotPasswordRequest.getParameter("email");
        String token = userForgotPasswordService.generateSimpleToken();
        userForgotPasswordService.updateResetPasswordToken(token,email);
        String resetPasswordLink =  Utility.getSiteURL(forgotPasswordRequest) +"/reset_password?token=" + token;
        userForgotPasswordService.sendEmail(email,resetPasswordLink);
        return "sentEmail";
    }

    @Override
    public String processResetPassword(HttpServletRequest forgotPasswordRequest){
        String token = forgotPasswordRequest.getParameter("token");
        String password = forgotPasswordRequest.getParameter("password");
        User user = userForgotPasswordService.getByResetPasswordToken(token);
        if(user == null){
            return "InvalidToken";
        }
        userForgotPasswordService.updatePasswordUser(user,password);
        return "Password changed successfully";
    }

    @Override
    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
        User user = userForgotPasswordService.getByResetPasswordToken(token);
        if(user == null){
            return "InvalidToken";
        }
        model.addAttribute("token",token);
        return "resetPasswordForm";
    }

    @Override
    public String register(HttpServletRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getParameter("username"))) {
            return "UsernameExist";
        }
        if(userRepository.existsByEmail(registerRequest.getParameter("email"))){
            return "EmailExist";
        }
        User user = new User(registerRequest.getParameter("username"),registerRequest.getParameter("email"),
                encoder.encode(registerRequest.getParameter("password")));
        userRepository.save(user);
        return "Succesfull";
    }
}
