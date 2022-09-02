package com.latebloomers.MovieInMine.controller;

import com.latebloomers.MovieInMine.config.jwt.JwtTokenProvider;
import com.latebloomers.MovieInMine.model.User;
import com.latebloomers.MovieInMine.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class AccountController {

    @Autowired
    private UserRepository userRepository;

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;



//    @GetMapping("/signin")
//    public String signginForm(){
//        System.out.println("registerForm In");
//        return "loginForm";
//    }


    // 회원가입
    @PostMapping("/signup")
    public String signUp(@RequestBody User user){
        log.info("AccountController.signUp");
        log.info(user.toString());
        String rawPassword = user.getPassword();
        String encPassword = bcryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        userRepository.save(user);
        return "signup success";
    }


    // 로그인 -> 프론트 로그인폼 URL로 리다이렉트
    @GetMapping("/signin")
    public void redirectSignIn(HttpServletResponse response) throws IOException {
        String frontSignInURL = "https://mim.hyeokho.me/users/signin/";
        response.sendRedirect(frontSignInURL);
    }

    // 로그인 수행
    @PostMapping("/signin")
    public String signIn(@RequestBody Map<String, String> user){
        log.info("username = {}", user.get("username"));
        User member = userRepository.findByUsername(user.get("username"));
        return jwtTokenProvider.createToken(member.getUsername());
    }


    // 회원탈퇴
    @DeleteMapping("/withdraw")
    public String withdraw(@RequestBody User user) {
        return "";

    }


}
