package id.ac.ui.cs.advprog.soulcatcher.service;

import id.ac.ui.cs.advprog.soulcatcher.payload.LoginRequest;
import id.ac.ui.cs.advprog.soulcatcher.payload.RegisterRequest;

public interface AuthenticationService {
    String login(LoginRequest loginRequest);
    String register(RegisterRequest registerRequest);
}
