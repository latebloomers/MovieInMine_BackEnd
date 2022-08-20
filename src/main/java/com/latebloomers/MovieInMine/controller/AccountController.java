package com.latebloomers.MovieInMine.controller;

import com.latebloomers.MovieInMine.model.User;
import com.latebloomers.MovieInMine.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller()
@RequestMapping("/users")
public class AccountController {

    @Autowired
    private UserRepository userRepository;

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

    @PostMapping("/signup")
    public @ResponseBody String join(User user){
        System.out.println("user = " + user);
        String rawPassword = user.getPassword();
        String encPassword = bcryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        userRepository.save(user);
        return "join";
    }


}
