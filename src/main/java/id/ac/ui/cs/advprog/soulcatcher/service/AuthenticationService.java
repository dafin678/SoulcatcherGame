package id.ac.ui.cs.advprog.soulcatcher.service;

import id.ac.ui.cs.advprog.soulcatcher.exception.UserNotFoundException;
import id.ac.ui.cs.advprog.soulcatcher.payload.ForgotPasswordRequest;
import id.ac.ui.cs.advprog.soulcatcher.payload.LoginRequest;
import id.ac.ui.cs.advprog.soulcatcher.payload.RegisterRequest;

import javax.mail.MessagingException;

public interface AuthenticationService {
    String login(LoginRequest loginRequest);
    String register(RegisterRequest registerRequest);
    String processForgotPassword( ForgotPasswordRequest forgotPasswordRequest) throws UserNotFoundException, MessagingException;
    String processResetPassword( ForgotPasswordRequest forgotPasswordRequest);
}
