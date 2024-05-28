package com.demo.gram.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Log4j2
@Component
public class JWTUtil {

  private Key secretKey;
  private static final long EXPIRE_DURATION = 1000 * 60 * 60 * 24 * 30; // 30 days in milliseconds

  @PostConstruct
  public void init() {
    this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512); // Automatically generate a secret key
  }

  public String generateToken(String content) {
    return Jwts.builder()
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
        .claim("sub", content)
        .signWith(secretKey, SignatureAlgorithm.HS512)
        .compact();
  }

  public String validateAndExtract(String tokenStr) {
    Claims claims = Jwts.parser()
        .setSigningKey(secretKey)
        .build()
        .parseClaimsJws(tokenStr)
        .getBody();
    return claims.get("sub", String.class);
  }
}
