package com.latebloomers.MovieInMine.controller;

import com.latebloomers.MovieInMine.model.User;
import com.latebloomers.MovieInMine.service.UserService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Slf4j
@Controller()
@RequestMapping("/users")
public class AccountController {

    @Autowired
    private UserService userService;

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

    @PostMapping("/signup")
    public @ResponseBody String signUp(@RequestParam("username") String username,
                         @RequestParam("nickname") String nickname,
                         @RequestParam("password") String password
    )throws SQLException {
        System.out.println("AccountController.signUp");
        System.out.println("username = " + username);
        System.out.println("nickname = " + nickname);
        System.out.println("password = " + password);

//        log.info(user.toString());
//        userService.save(user);

        // 회원가입 후 홈으로 redirect
        return "redirect:/";
    }
}
