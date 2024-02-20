package com.supercoding.brandiStory.config.security;

import com.supercoding.brandiStory.service.exceptions.ErrorMessage;
import com.supercoding.brandiStory.service.exceptions.JwtTokenException;
import com.supercoding.brandiStory.service.exceptions.NotAcceptException;
import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenProvider {
    private String accessSecretKey;
    private String refreshSecretKey;
    @Value("${jwt.source.access-secret-key}")
    private String accessSecretKeySource;
    @Value("${jwt.source.refresh-secret-key}")
    private String refreshSecretKeySource;
    private long accessTokenValidMillisecond = Duration.ofMinutes(30).toMillis();
    private long refreshTokenValidMillisecond = Duration.ofDays(7).toMillis();;

    private final UserDetailsService userDetailsService;

    @PostConstruct
    public void setUp(){
        accessSecretKey = Base64.getEncoder()
                .encodeToString(accessSecretKeySource.getBytes());
        refreshSecretKey = Base64.getEncoder()
                .encodeToString(refreshSecretKeySource.getBytes());
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    public String createAccessToken(String email, List<String> roles) {
        return createToken(email, roles, accessTokenValidMillisecond, accessSecretKey);
    }

    public String createRefreshToken(String email, List<String> roles) {
        return createToken(email, roles, refreshTokenValidMillisecond, refreshSecretKey);
    }

    public String createToken(String email, List<String> roles, long tokenValidMillisecond, String secretKey) {
        // JWT payload에 들어갈 내용
        Claims claims = Jwts.claims()
                .setSubject(email);

        claims.put("roles", roles);

        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMillisecond))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean validateAccessToken(String jwtToken) {
        log.info("jwtToken: {}", jwtToken);
        return validateToken(jwtToken, accessSecretKey);
    }

    public boolean expireAccessToken(String accessToken) throws JwtTokenException{
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(accessSecretKey)
                    .parseClaimsJws(accessToken)
                    .getBody();

            log.info("before token expire time: {}", claims.getExpiration());

            claims.setExpiration(new Date());

            log.info("after token expire time: {}", claims.getExpiration());

            return true;
        } catch (MalformedJwtException e) {
            throw new JwtTokenException(ErrorMessage.WRONG_TYPE_TOKEN.getErrMsg());
        } catch (ExpiredJwtException e) {
            throw new JwtTokenException(ErrorMessage.EXPIRED_TOKEN.getErrMsg());
        } catch (UnsupportedJwtException e) {
            throw new JwtTokenException(ErrorMessage.UNSUPPORTED_TOKEN.getErrMsg());
        } catch (SignatureException e) {
            throw new JwtTokenException(ErrorMessage.SIGNATURE_FAIL_TOKEN.getErrMsg());
        } catch (Exception e) {
            log.error("e.getClass() : {}", e.getClass());
            log.error("expireAccessToken error: {}", e.getMessage());
            throw new JwtTokenException(e.getMessage());
        }
    }

    public boolean validateRefreshToken(String jwtToken) {
        return validateToken(jwtToken, refreshSecretKey);
    }

    public boolean validateToken(String jwtToken, String secretKey) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(jwtToken)
                    .getBody();

            Date now = new Date();

            return claims.getExpiration()
                    .after(now);
        } catch (SignatureException e) {
            log.info("SignatureException");
            throw new JwtException(ErrorMessage.WRONG_TYPE_TOKEN.getErrMsg());
        } catch (MalformedJwtException e) {
            log.info("MalformedJwtException");
            throw new JwtException(ErrorMessage.UNSUPPORTED_TOKEN.getErrMsg());
        } catch (ExpiredJwtException e) {
            log.info("ExpiredJwtException");
            throw new JwtException(ErrorMessage.EXPIRED_TOKEN.getErrMsg());
        } catch (IllegalArgumentException e) {
            log.info("IllegalArgumentException");
            throw new JwtException(ErrorMessage.UNKNOWN_ERROR.getErrMsg());
        }
    }

    public Authentication getAuthentication(String jwtToken) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUserEmail(jwtToken));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getUserEmail(String jwtToken) {
        return Jwts.parser()
                .setSigningKey(accessSecretKey)
                .parseClaimsJws(jwtToken)
                .getBody()
                .getSubject();
    }

    public void setAccessTokenValidMillisecond(String accessTokenValidMillisecond) {
        this.accessTokenValidMillisecond = Long.parseLong(accessTokenValidMillisecond);
    }

    public void setRefreshTokenValidMillisecond(String refreshTokenValidMillisecond) {
        this.refreshTokenValidMillisecond = Long.parseLong(refreshTokenValidMillisecond);
    }

    public String getAccessTokenInfo(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(accessSecretKey)
                .parseClaimsJws(token)
                .getBody();

        // 클레임 객체에 있는 모든 정보를 출력합니다.
        String tokenInfo = "토큰에 포함된 정보: ";
        for (String key : claims.keySet()) {
            if (key.equals("iat") || key.equals("exp")){
                // Unix 시간을 LocalDateTime으로 변환
                long unixTime = Long.parseLong(String.valueOf(claims.get(key)));
                LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(unixTime), ZoneId.systemDefault());
                tokenInfo += (key + ": " + dateTime + " | ");
                continue;
            }

            tokenInfo += (key + ": " + claims.get(key) + " | ");
        }
        return tokenInfo;
    }
}
