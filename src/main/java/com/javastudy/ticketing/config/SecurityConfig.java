//package com.javastudy.ticketing.config;
//
//import com.javastudy.ticketing.jwt.JwtAccessDeniedHandler;
//import com.javastudy.ticketing.jwt.JwtAuthenticationEntryPoint;
//import com.javastudy.ticketing.jwt.JwtSecurityConfig;
//import com.javastudy.ticketing.jwt.TokenProvider;
//import org.apache.catalina.filters.CorsFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@EnableWebSecurity
//@Configuration
////@EnableGlobalAuthentication//preAuthorized어노테이션을 메소드단위로 추가하기 위해서 적용
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    private final TokenProvider tokenProvider;
////    private final CorsFilter corsFilter;
//    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
//
//    public SecurityConfig(
//            TokenProvider tokenProvider,
////            CorsFilter corsFilter,
//            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
//            JwtAccessDeniedHandler jwtAccessDeniedHandler
//    ) {
//        this.tokenProvider = tokenProvider;
////        this.corsFilter = corsFilter;
//        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
//        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
//    }
//
//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring()
//                .antMatchers(
//                        "/signupform"
//                        ,"/signup"
//                        ,"/"
//                        ,"/error"
//                        ,"/loginform"
//                        ,"/login"
//
//                );
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                // token을 사용하는 방식이기 때문에 csrf를 disable합니다.
//                .csrf().disable()
//
////                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
//
//                .exceptionHandling()
//                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                .accessDeniedHandler(jwtAccessDeniedHandler)
//
//                // enable h2-console
//                .and()
//                .headers()
//                .frameOptions()
//                .sameOrigin()
//
//                // 세션을 사용하지 않기 때문에 STATELESS로 설정
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//
//                .and()
//                .authorizeRequests()
//                .antMatchers("/api/hello").permitAll()
//                .antMatchers("/api/authenticate").permitAll()
//                .antMatchers("/api/signup").permitAll()
//
//                .anyRequest().authenticated()
//
//                .and()
//                .apply(new JwtSecurityConfig(tokenProvider));
//    }
//}