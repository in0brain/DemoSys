package com.example.demosys.common.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Component
public class JwtTokenService {

    private final JwtProperties props;

    public JwtTokenService(JwtProperties props) {
        this.props = props;
    }

    public String createToken(String subject, Map<String, Object> claims) {
        long now = System.currentTimeMillis();
        long expMs = now + (props.getTtlSeconds() * 1000L);

        byte[] secretBytes = props.getSecret().getBytes(StandardCharsets.UTF_8);
        SecretKeySpec key = new SecretKeySpec(secretBytes, SignatureAlgorithm.HS256.getJcaName());

        return Jwts.builder()
                .setSubject(subject)
                .setId(UUID.randomUUID().toString())      // jti
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(expMs))
                .addClaims(claims)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public long getTtlSeconds() {
        return props.getTtlSeconds();
    }
}
