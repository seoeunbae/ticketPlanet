//package com.javastudy.ticketing.jwt;
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.Date;
//import java.util.stream.Collectors;
//@Component
//public class TokenProvider implements InitializingBean {
//    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
//
//    private static final String AUTHORITIES_KEY = "auth";
//
//    private final String secret;
//    private final long tokenValidityInMilliseconds;
//
//    private Key key;
//
//
//    public TokenProvider(
//            @Value("${jwt.secret}") String secret,//주입
//            @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds) {
//        this.secret = secret;
//        this.tokenValidityInMilliseconds = tokenValidityInSeconds * 1000;
//    }
//
//    @Override
//    public void afterPropertiesSet() {
//        byte[] keyBytes = Decoders.BASE64.decode(secret);//주입받은 secret값을 base63로 디코딩
//        this.key = Keys.hmacShaKeyFor(keyBytes);//한 값을 key에 할당
//    }
//
//    public String createToken(Authentication authentication) {//권한정보를 담은 토큰을 생성
//        String authorities = authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(","));
//
//        long now = (new Date()).getTime();//만료시간을 추가해줌
//        Date validity = new Date(now + this.tokenValidityInMilliseconds);
//
//        return Jwts.builder()
//                .setSubject(authentication.getName())
//                .claim(AUTHORITIES_KEY, authorities)
//                .signWith(key, SignatureAlgorithm.HS512)
//                .setExpiration(validity)
//                .compact();//jwt토큰을 생성해서 리턴
//    }
//
//    public Authentication getAuthentication(String token) {//토큰을 받아서 클레임생성, 이를 통해 유저객체를 만들어서 authentication객체를 리턴
//        Claims claims = Jwts
//                .parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//
//        Collection<? extends GrantedAuthority> authorities =
//                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
//                        .map(SimpleGrantedAuthority::new)
//                        .collect(Collectors.toList());
//
//        User principal = new User(claims.getSubject(), "", authorities);
//
//        return new UsernamePasswordAuthenticationToken(principal, token, authorities);//유저객체, 토큰,권한정보
//    }
//
//    public boolean validateToken(String token) {//토큰이 유효한지 검사
//        try {
//            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
//            return true;
//        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
//            logger.info("잘못된 JWT 서명입니다.");
//        } catch (ExpiredJwtException e) {
//            logger.info("만료된 JWT 토큰입니다.");
//        } catch (UnsupportedJwtException e) {
//            logger.info("지원되지 않는 JWT 토큰입니다.");
//        } catch (IllegalArgumentException e) {
//            logger.info("JWT 토큰이 잘못되었습니다.");
//        }
//        return false;
//    }
//}
