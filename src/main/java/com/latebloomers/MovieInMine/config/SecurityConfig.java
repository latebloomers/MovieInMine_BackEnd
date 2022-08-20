package com.latebloomers.MovieInMine.config;

import com.latebloomers.MovieInMine.config.jwt.JwtAuthenticationFilter;
import com.latebloomers.MovieInMine.config.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록된다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/", "/users/**", "/login").permitAll()
                .anyRequest().authenticated()
                .and()
//            .formLogin()
//                .loginPage("/users/signin")
//                .permitAll()
//                .loginProcessingUrl("/login") //login 주소가 호출이 되면 시큐리티 로그인이 실행된다.
//                .defaultSuccessUrl("/")
//                .and()
            .logout()
                .permitAll()
            .and()
            // JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 전에 넣는다
            .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                    UsernamePasswordAuthenticationFilter.class);
        // + 토큰에 저장된 유저정보를 활용하여야 하기 때문에 CustomUserDetailService 클래스를 생성합니다.
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    // Password 암호화를 위한 Encoder 설정
    @Bean
    public BCryptPasswordEncoder encoderPWD(){
        return new BCryptPasswordEncoder();
    }

}
