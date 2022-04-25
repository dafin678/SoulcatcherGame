// UNTUK TEST DI POSTMAN


//package id.ac.ui.cs.advprog.soulcatcher.controller;
//import javax.validation.Valid;
//import id.ac.ui.cs.advprog.soulcatcher.Security.JwtUtils;
//import id.ac.ui.cs.advprog.soulcatcher.model.User;
//import id.ac.ui.cs.advprog.soulcatcher.payload.JwtResponse;
//import id.ac.ui.cs.advprog.soulcatcher.payload.LoginRequest;
//import id.ac.ui.cs.advprog.soulcatcher.payload.MessageResponse;
//import id.ac.ui.cs.advprog.soulcatcher.payload.RegisterRequest;
//import id.ac.ui.cs.advprog.soulcatcher.repository.UserRepository;
//import id.ac.ui.cs.advprog.soulcatcher.service.UserDetailsImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "*", maxAge = 3600)
//@RestController
//@RequestMapping("/auth")
//public class AuthenticationController {
//    @Autowired
//    AuthenticationManager authenticationManager;
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    PasswordEncoder encoder;
//    @Autowired
//    JwtUtils jwtUtils;
//
//    @PostMapping("/login")
//    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtUtils.generateJwtToken(authentication);
//
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        return ResponseEntity.ok(new JwtResponse(jwt,
//                userDetails.getId(),
//                userDetails.getUsername()));
//    }
//    @PostMapping("/register")
//    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
//        if (userRepository.existsByUsername(registerRequest.getUsername())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Username is already taken!"));
//        }
//        if(userRepository.existsByEmail(registerRequest.getEmail())){
//            return ResponseEntity.badRequest().body(new MessageResponse(
//                    "Error: Email is already taken!"
//            ));
//        }
//        // Create new user's account
//        User user = new User(registerRequest.getUsername(),registerRequest.getEmail(),
//                encoder.encode(registerRequest.getPassword()));
//        userRepository.save(user);
//        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
//    }
//}
