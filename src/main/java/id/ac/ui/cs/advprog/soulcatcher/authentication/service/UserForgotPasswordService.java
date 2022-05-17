package id.ac.ui.cs.advprog.soulcatcher.authentication.service;

import id.ac.ui.cs.advprog.soulcatcher.authentication.model.User;
import id.ac.ui.cs.advprog.soulcatcher.authentication.exception.UserNotFoundException;

import javax.mail.MessagingException;

public interface UserForgotPasswordService {
    void updateResetPasswordToken(String token,String email) throws UserNotFoundException;
    User getByResetPasswordToken(String resetPasswordToken);
    void updatePasswordUser(User user, String newPassword);
    void sendEmail(String email, String resetPasswordLink) throws MessagingException;
    String generateSimpleToken();
}
