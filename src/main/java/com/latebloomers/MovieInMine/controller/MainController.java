package com.latebloomers.MovieInMine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(){
        String frontHomeURL = "http://mim.hyeokho.me/";
        return "redirect:" + frontHomeURL;
    }

}
