package backend.example.demo.service;

import backend.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService {
    public String generateToken(User user) {
       return UUID.randomUUID().toString();

    }
}
