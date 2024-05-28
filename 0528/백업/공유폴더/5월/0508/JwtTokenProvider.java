package com.example.GRaM.jwt;

import com.example.GRaM.details.CustomMemberDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtTokenProvider {

  @Value("${jwt.secret}")
  private String jwtSecretKey;

  @Value("${jwt.accessTokenExpirationTime}")
  private Long jwtAccessTokenExpirationTime;

  @Value("${jwt.refreshTokenExpirationTime}")
  private Long jwtRefreshTokenExpirationTime;

  public String generateAccessToken(Authentication authentication) {
    CustomMemberDetails customMemberDetails = (CustomMemberDetails) authentication.getPrincipal();
    Date expireDate = new Date(new Date().getTime() + jwtAccessTokenExpirationTime);
    return Jwts.builder()
        .setSubject(customMemberDetails.getUsername())
        .claim("user-id", customMemberDetails.getId())
        .claim("user-email", customMemberDetails.getEmail())
        .setIssuedAt(new Date())
        .setExpiration(expireDate)
        .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
        .compact();
  }

  public String generateRefreshToken(Authentication authentication) {
    CustomMemberDetails customMemberDetails = (CustomMemberDetails) authentication.getPrincipal();
    Date expiryDate = new Date(new Date().getTime() + jwtRefreshTokenExpirationTime);
    return Jwts.builder()
        .setSubject(customMemberDetails.getUsername())
        .claim("user-id", customMemberDetails.getId())
        .claim("user-email", customMemberDetails.getEmail())
        .setIssuedAt(new Date())
        .setExpiration(expiryDate)
        .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
        .compact();
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public Long getUserIdFromToken(String token) {
    return getClaimFromToken(token, claims -> claims.get("user-id", Long.class));
  }

  public String getUsernameFromToken(String token) {
    return getClaimFromToken(token, Claims::getSubject);
  }

  public String getUserEmailFromToken(String token) {
    return getClaimFromToken(token, claims -> claims.get("user-email", String.class));
  }

  public Date getExpirationFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }

  private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = parseJwtToken(token);
    return claims != null ? claimsResolver.apply(claims) : null;
  }

  private Claims parseJwtToken(String token) {
    try {
      return Jwts.parser()
          .setSigningKey(jwtSecretKey)
          .parseClaimsJws(token)
          .getBody();
    } catch (Exception e) {
      // 토큰 파싱 중 오류가 발생할 경우에 대한 예외 처리
      e.printStackTrace();
      return null;
    }
  }
}