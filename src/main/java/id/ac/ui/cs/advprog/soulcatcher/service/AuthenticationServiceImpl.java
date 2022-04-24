package id.ac.ui.cs.advprog.soulcatcher.service;

import id.ac.ui.cs.advprog.soulcatcher.Security.JwtUtils;
import id.ac.ui.cs.advprog.soulcatcher.model.User;
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

    @Override
    public String login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        return jwt;
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
