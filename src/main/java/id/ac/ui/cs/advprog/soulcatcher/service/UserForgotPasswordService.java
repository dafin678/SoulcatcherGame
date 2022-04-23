package id.ac.ui.cs.advprog.soulcatcher.service;

import id.ac.ui.cs.advprog.soulcatcher.exception.UserNotFoundException;
import id.ac.ui.cs.advprog.soulcatcher.model.User;

import javax.mail.MessagingException;

public interface UserForgotPasswordService {
    void updateResetPasswordToken(String token,String email) throws UserNotFoundException;
    User getByResetPasswordToken(String resetPasswordToken);
    void updatePasswordUser(User user, String newPassword);
    void sendEmail(String email, String resetPasswordLink) throws MessagingException;
}
