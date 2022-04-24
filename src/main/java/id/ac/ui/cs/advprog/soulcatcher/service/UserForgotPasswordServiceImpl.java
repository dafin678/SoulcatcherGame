package id.ac.ui.cs.advprog.soulcatcher.service;

import id.ac.ui.cs.advprog.soulcatcher.exception.UserNotFoundException;
import id.ac.ui.cs.advprog.soulcatcher.model.User;
import id.ac.ui.cs.advprog.soulcatcher.repository.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class UserForgotPasswordServiceImpl implements UserForgotPasswordService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void updateResetPasswordToken(String token,String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email);
        //System.out.println(userRepository.findAll());
        if(user!=null){
            user.setResetPasswordToken(token);
            userRepository.save(user);
        } else{
            throw new UserNotFoundException("Couldn't find any user with email " + email);
        }
    }

    @Override
    public User getByResetPasswordToken(String resetPasswordToken){
        return userRepository.findByResetPasswordToken(resetPasswordToken);
    }

    @Override
    public void updatePasswordUser(User user, String newPassword){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        user.setResetPasswordToken(null);
        userRepository.save(user);
    }

    @Override
    public void sendEmail(String email, String resetPasswordLink) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("contact@soulcatcher.com");
        helper.setTo(email);

        String subject = "Here's link to reset your password";
        String content = "<p>Hello,<p>"
                + "<p>You have requested to reset your password</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><b><a href=\"" + resetPasswordLink + "\">Change my password</a><b></p>";
        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);

    }

    @Override
    public String generateSimpleToken() {
        return RandomString.make(45);
    }


}
