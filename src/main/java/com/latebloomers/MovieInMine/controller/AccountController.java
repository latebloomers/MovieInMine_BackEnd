package com.latebloomers.MovieInMine.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller()
@RequestMapping("/users")
public class AccountController {

    @GetMapping("/signin")
    public String redirectSignIn(){
        String frontSigninURL = "http://mim.hyeokho.me/users/signin/";
        return "redirect:" + frontSigninURL;
    }

    @GetMapping("/signup")
    public @ResponseBody String register(){
        System.out.println("register com");
        return "come in";
    }

}
