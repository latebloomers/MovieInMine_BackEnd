package com.latebloomers.MovieInMine.controller;

import com.latebloomers.MovieInMine.config.jwt.JwtTokenProvider;
import com.latebloomers.MovieInMine.model.User;
import com.latebloomers.MovieInMine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Controller()
@RequestMapping("/users")
@RequiredArgsConstructor
public class AccountController {

    @Autowired
    private UserRepository userRepository;

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;

//    @GetMapping("/signin")
//    public String redirectSignIn(){
//        String frontSigninURL = "http://mim.hyeokho.me/users/signin/";
//        return "redirect:" + frontSigninURL;
//    }

    @GetMapping("/signin")
    public String signginForm(){
        System.out.println("registerForm In");
        return "loginForm";
    }

    // 로그인 수행
    @PostMapping("/signin")
    public @ResponseBody String signin(@RequestBody Map<String, String> user){
        log.info("username = {}", user.get("username"));
        User member = userRepository.findByUsername(user.get("username"));
        return jwtTokenProvider.createToken(member.getUsername());
    }

    @PostMapping("/signup")
    public @ResponseBody String join(User user){
        String rawPassword = user.getPassword();
        String encPassword = bcryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        userRepository.save(user);
        return "join";
    }


}
