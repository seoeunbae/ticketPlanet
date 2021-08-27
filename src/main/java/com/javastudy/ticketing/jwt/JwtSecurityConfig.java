//package com.javastudy.ticketing.jwt;
//import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.DefaultSecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {//TokenProvider,JwtFilter 를 securityConfig에 적용할때 사용할 jwtsecurityconfig클래스 추가
//
//    private TokenProvider tokenProvider;
//
//    public JwtSecurityConfig(TokenProvider tokenProvider){
//        this.tokenProvider = tokenProvider;
//    }
//
//
//    @Override
//    public void configure(HttpSecurity http) {//jwtfilter를 통해 security로직에 필터를 등록합니다.
//        JwtFilter customFilter = new JwtFilter(tokenProvider);
//        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
//    }
//}
