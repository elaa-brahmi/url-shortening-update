package backend.example.demo.service;

import backend.example.demo.model.User;
import backend.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    public final UserRepository userRepository;

    public User LoginRegisterByGoogleOAuth2(OAuth2AuthenticationToken token) {
        OAuth2User oAuth2User = token.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        log.info("user from oauth2 ",oAuth2User);
        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null) {
            return user;
        }
        user = new User();
        user.setEmail(email);
        user.setFullName(name);
        return userRepository.save(user);
    }
}
