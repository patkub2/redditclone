package pl.kanterbow.redditClone.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kanterbow.redditClone.dto.RegisterRequest;
import pl.kanterbow.redditClone.model.User;

import java.time.Instant;


@Service
public class AuthService {
    private PasswordEncoder passwordEncoder;
public void signup (RegisterRequest registerRequest){
    User user = new User();
    user.setUsername(registerRequest.getUsername());
    user.setEmail(registerRequest.getEmail());
    user.setPassword(passwordEncoder.encode(registerRequest.getPassword()) );
    user.setCreated(Instant.now());
    user.setEnabled(false);
}
}
