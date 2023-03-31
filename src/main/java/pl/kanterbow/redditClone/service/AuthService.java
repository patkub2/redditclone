package pl.kanterbow.redditClone.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kanterbow.redditClone.dto.RegisterRequest;
import pl.kanterbow.redditClone.model.User;
import pl.kanterbow.redditClone.model.VerificationToken;
import pl.kanterbow.redditClone.repository.UserRepository;
import pl.kanterbow.redditClone.repository.VerificationTokenRepository;

import java.time.Instant;
import java.util.UUID;


@Service
@AllArgsConstructor
public class AuthService {


    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
public void signup (RegisterRequest registerRequest){
    User user = new User();
    user.setUsername(registerRequest.getUsername());
    user.setEmail(registerRequest.getEmail());
    user.setPassword(passwordEncoder.encode(registerRequest.getPassword()) );
    user.setCreated(Instant.now());
    user.setEnabled(false);
    userRepository.save(user);

    String token = generateVerificationToken(user);

}
    private String generateVerificationToken(User user){
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;
    }
}
