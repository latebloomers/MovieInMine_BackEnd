package com.example.sideproject.controller;

import com.example.sideproject.model.ArticleDto;
import com.example.sideproject.model.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/article")
public class ArticleController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

//    @GetMapping()
//    public ResponseEntity<List<ArticleDto>> getBoard() throws SQLException {
//        return new ResponseEntity<List<ArticleDto>>(articleService.getArticles(), HttpStatus.OK);
//    }

//    @GetMapping()
//    public List<ArticleDto> getArticles2() throws SQLException {
//        System.out.println("ArticleController.getArticles");
//        return articleService.getArticles();
//    }

    @GetMapping()
    public ResponseEntity<List<ArticleDto>> getArticles() throws SQLException {
        System.out.println("ArticleController.getArticles");
        return new ResponseEntity<>(articleService.getArticles(), HttpStatus.OK);
    }

//    @GetMapping("{articleId}")
//    public ArticleDto getArticle(@PathVariable("articleId") String articleId) throws SQLException {
//        return articleService.getArticle(Integer.parseInt(articleId));
//    }
//
//    @PostMapping()
//    public ResponseEntity<String> createArticle(@RequestParam("userId") String userId,
//                                                @RequestParam("title") String title,
//                                                @RequestParam("content") String content) throws Exception {
//        System.out.println("ArticleController.createArticle");
//
//        ArticleDto articleDto = new ArticleDto();
//        articleDto.setUserId(Integer.parseInt(userId));
//        articleDto.setTitle(title);
//        articleDto.setContent(content);
//        System.out.println(articleDto);
//        if(articleService.createArticle(articleDto)) {
//            return new ResponseEntity<String>("success", HttpStatus.OK);
//        }
//        else {
//            System.out.println("Error - create article");
//            return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
//        }
//    }


}
