package backend.example.demo.controller;

import backend.example.demo.model.User;
import backend.example.demo.service.TokenService;
import backend.example.demo.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final TokenService tokenService;

    @GetMapping("/login/google")
    public ResponseEntity<String> loginGoogleAuth(HttpServletResponse response) throws IOException {
        response.sendRedirect("/oauth2/authorization/google");

        return ResponseEntity.ok("Redirecting ..");
    }

    @GetMapping("/loginSuccess")
    public void userInfo(HttpServletResponse response,OAuth2AuthenticationToken token) throws IOException {
        User user = userService.LoginRegisterByGoogleOAuth2(token);
        String jwt =tokenService.generateToken(user);

        String redirectUrl = UriComponentsBuilder
                .fromUriString("http://localhost:4200/main-page")
                .queryParam("token", jwt)
                .queryParam("id", user.getId())
                .queryParam("fullName", URLEncoder.encode(user.getFullName(), StandardCharsets.UTF_8))
                .build()
                .toUriString();

        response.sendRedirect(redirectUrl);

    }

}
