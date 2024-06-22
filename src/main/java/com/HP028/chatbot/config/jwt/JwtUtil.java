package com.HP028.chatbot.config.jwt;

import com.HP028.chatbot.common.response.ApiFailStatus;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {

    private final SecretKey secretKey;
    private final long accessTokenExpTime;

    public JwtUtil(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration_time}") long accessTokenExpTime){
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
        this.accessTokenExpTime = accessTokenExpTime;
    }

    public String getUsername(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("username", String.class);
    }

    public String getRole(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }

    public Boolean isExpired(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    public String createJwt(String username, String role) {

        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime tokenValidity = now.plusSeconds(accessTokenExpTime);

        return Jwts.builder()
                .claim("username", username)
                .claim("role", role)
                .issuedAt(Date.from(now.toInstant()))
                .expiration(Date.from(tokenValidity.toInstant()))
                .signWith(secretKey)
                .compact();
    }

    public ApiFailStatus validateToken(String token) {
        ApiFailStatus apiFailStatus;
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getBody();
            return null;
        } catch (io.jsonwebtoken.security.SecurityException e) {
            log.info("Invalid JWT Token", e);
            apiFailStatus = ApiFailStatus.INVALID_TOKEN;
        }catch (MalformedJwtException e){
            log.info("Malformed JWT Token", e);
            apiFailStatus = ApiFailStatus.INVALID_TOKEN;
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
            apiFailStatus = ApiFailStatus.EXPIRED_TOKEN;
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
            apiFailStatus = ApiFailStatus.UNSUPPORTED_TOKEN;
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
            apiFailStatus = ApiFailStatus.EMPTY_CLAIMS;
        }
        return apiFailStatus;
    }

    public static String getMemberEmailFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            return userDetails.getMember().getEmail();
        }
        throw new RuntimeException("User not authenticated");
    }
}