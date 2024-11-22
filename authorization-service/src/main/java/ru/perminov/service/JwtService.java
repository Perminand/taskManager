package ru.perminov.service;

import com.auth0.jwt.JWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.Value;

import java.util.Map;

public class JwtService {

    public String generateJwt(Authentication authentication) {
        return Jwts.builder()
                .setClaims(
                        Map.of(
                                ClaimField
                        )
                )
    }
}
