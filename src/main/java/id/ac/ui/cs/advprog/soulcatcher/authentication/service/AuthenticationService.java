package id.ac.ui.cs.advprog.soulcatcher.authentication.service;

import id.ac.ui.cs.advprog.soulcatcher.authentication.exception.UserNotFoundException;
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
