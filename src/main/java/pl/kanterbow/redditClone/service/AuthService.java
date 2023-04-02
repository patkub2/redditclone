package pl.kanterbow.redditClone.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kanterbow.redditClone.dto.RegisterRequest;
import pl.kanterbow.redditClone.model.NotificationEmail;
import pl.kanterbow.redditClone.model.User;
import pl.kanterbow.redditClone.model.VerificationToken;
import pl.kanterbow.redditClone.repository.UserRepository;
import pl.kanterbow.redditClone.repository.VerificationTokenRepository;

import java.time.Instant;
import java.util.UUID;


@Service
@AllArgsConstructor
@Slf4j
public class AuthService {


    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

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
    mailService.sendMail(new NotificationEmail("Please Active your Account", user.getEmail(),"Thank you for signing up to Spring Reddit, " +
            "please click on the below url to activate your account : " +
            "http://localhost:8080/api/auth/accountVerification/" + token ));

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
