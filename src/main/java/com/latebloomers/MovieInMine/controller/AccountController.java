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
        log.info("METHOD: (POST)signUp");
        //log.info(user.toString());

        String rawPassword = user.getPassword();
        String encPassword = bcryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        userRepository.save(user);
        return "signup success";

    }


    // 로그인 -> 프론트 로그인폼 URL로 리다이렉트
    @GetMapping("/signin")
    public void redirectSignIn(HttpServletResponse response) throws IOException {
        log.info("METHOD: (GET)redirectSignIn");

        String frontSignInURL = "https://mim.hyeokho.me/users/signin/";
        response.sendRedirect(frontSignInURL);

    }
    // 로그인 수행
    @PostMapping("/signin")
    public String signIn(@RequestBody Map<String, String> user){
        log.info("METHOD: (POST)signIn");

        //log.info("username = {}", user.get("username"));
        User member = userRepository.findByUsername(user.get("username"));
        return jwtTokenProvider.createToken(member.getUsername());

    }
    // 회원탈퇴
    // 요청한 userId로 DB에서 해당 회원을 삭제하고 성공메시지 리턴
    // 요청한 회원을 찾을 수 없다면 에러메시지 리턴
    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@RequestParam String userId) {
        log.info("METHOD: deleteUser");
        log.info(userId);

        Map<String, String> error = new HashMap<>();
        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(userId));

        if (optionalUser.isPresent()){
            userRepository.delete(optionalUser.get());
            return new ResponseEntity<>("회원탈퇴 성공", HttpStatus.OK);

        } else {
            error.put("errorMessage", "에러: 요청한 회원을 찾을 수 없습니다.");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

    }
    // 회원 찾기
    // 요청한 userId로 DB에서 해당 회원을 찾아 JSON 형태로 리턴
    // 요청한 회원을 찾을 수 없다면 에러메시지 리턴
    @GetMapping("/finduser")
    public ResponseEntity findUser(@RequestParam String userId){
        log.info("METHOD: findUser");

        Map<String, String> error = new HashMap<>();
        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(userId));

        if(optionalUser.isPresent()) {
            return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);

        } else {
            error.put("errorMessage", "에러: 요청한 회원을 찾을 수 없습니다.");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }

}
