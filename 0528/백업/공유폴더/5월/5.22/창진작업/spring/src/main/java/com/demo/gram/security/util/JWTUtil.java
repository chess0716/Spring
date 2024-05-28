package com.demo.gram.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Log4j2
public class JWTUtil {

  private final byte[] secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded();
  private final long expire = 60 * 24 * 30;

  public String generateToken(String content) {
    return Jwts.builder()
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + expire * 60 * 1000))
        .claim("sub", content)
        .signWith(Keys.hmacShaKeyFor(secretKey), SignatureAlgorithm.HS512)
        .compact();
  }

  public String validateAndExtract(String tokenStr) {
    Claims claims = Jwts.parser()
        .setSigningKey(Keys.hmacShaKeyFor(secretKey))
        .build()
        .parseClaimsJws(tokenStr)
        .getBody();
    return claims.get("sub", String.class);
  }
}
