package com.latebloomers.MovieInMine.controller;

import com.latebloomers.MovieInMine.config.jwt.JwtTokenProvider;
import com.latebloomers.MovieInMine.model.ArticleDto;
import com.latebloomers.MovieInMine.model.User;
import com.latebloomers.MovieInMine.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
    // 회원 찾기
    // 전달받은 User.id로 DB에 해당 User를 찾아 JSON 형태로 Return
    @GetMapping("/finduser")
    public ResponseEntity findUser(@RequestParam int userId){

        System.out.println("AccountController.findUser");

        Map<String, String> error = new HashMap<>();
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isPresent()) {
            return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
        } else {
            error.put("findUserError", "에러: 요청한 사용자를 찾을 수 없습니다.");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }


    }

}
