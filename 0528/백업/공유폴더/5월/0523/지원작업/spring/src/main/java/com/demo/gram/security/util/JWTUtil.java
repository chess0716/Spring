package com.demo.gram.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Log4j2
@Component
public class JWTUtil {

  @Value("${jwt.secretKey}")
  private String secretKey;

  private final long expire = 60 * 24 * 30;

  public String generateToken(String content) {
    return Jwts.builder()
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + expire * 60 * 1000))
        .claim("sub", content)
        .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS512)
        .compact();
  }

  public String validateAndExtract(String tokenStr) {
    Claims claims = Jwts.parser()
        .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
        .build()
        .parseClaimsJws(tokenStr)
        .getBody();
    return claims.get("sub", String.class);
  }
}
