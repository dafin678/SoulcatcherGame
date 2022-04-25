package id.ac.ui.cs.advprog.soulcatcher.service;

import id.ac.ui.cs.advprog.soulcatcher.exception.UserNotFoundException;
import id.ac.ui.cs.advprog.soulcatcher.payload.ForgotPasswordRequest;
import id.ac.ui.cs.advprog.soulcatcher.payload.LoginRequest;
import id.ac.ui.cs.advprog.soulcatcher.payload.RegisterRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

public interface AuthenticationService {
    String login(HttpServletRequest loginRequest);
    String register(HttpServletRequest registerRequest);
    String processForgotPassword(HttpServletRequest forgotPasswordRequest) throws UserNotFoundException, MessagingException;
    String processResetPassword(HttpServletRequest forgotPasswordRequest);
    String showResetPasswordForm(@Param(value = "token") String token, Model model);
}
