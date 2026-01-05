package be.ucll.chappl.users.application;

import be.ucll.chappl.users.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtAuthTokenService implements AuthTokenService {

    private static final String SECRET = "a06edb3a6453f0048371bfcf70cdaa02a725be2ef84eb1481c1c6553f950843f";
    private static final long EXPIRATION_MS = 24 * 60 * 60 * 1000;

    @Override
    public String generate(User user) {
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("email", user.getEmail())
                .claim("username", user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }
}
