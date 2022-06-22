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

    @GetMapping()
    public ResponseEntity<List<ArticleDto>> getArticles() throws SQLException {
        System.out.println("ArticleController.getArticles");
        return new ResponseEntity<>(articleService.getArticles(), HttpStatus.OK);
    }

    @GetMapping("{articleId}")
    public ResponseEntity<ArticleDto> getArticle(@PathVariable("articleId") int articleId) throws SQLException {
        return new ResponseEntity<>(articleService.getArticle(articleId), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> createArticle(@RequestBody ArticleDto articleDto) throws SQLException {
//        System.out.println("ArticleController.createArticle");
//        System.out.println(articleDto);
        if(articleService.createArticle(articleDto)) {
            return new ResponseEntity<String>("success", HttpStatus.OK);
        }
        else {
            System.out.println("Error - create article");
            return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("{articleId}")
    public ResponseEntity<String> updateArticle(@RequestBody ArticleDto article) throws SQLException{

        if(articleService.updateArticle(article)) {
            return new ResponseEntity<String>("success", HttpStatus.OK);
        }
        return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{articleId}")
    public ResponseEntity<String> deleteArticle(@PathVariable int articleId) throws SQLException {

        if(articleService.deleteArticle(articleId)){
            return new ResponseEntity<String>("Success", HttpStatus.OK);
        }
        return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
    }



//    HTTP POST Form 요청 : www-form-urlencoded 방식은 @RequestBody 사용 불가
//        @PostMapping()
//    public ResponseEntity<String> createArticle(@RequestParam("userId") String userId,
//                                                @RequestParam("title") String title,
//                                                @RequestParam("content") String content) throws Exception {

//        if(articleService.createArticle(articleDto)) {
//            return new ResponseEntity<String>("success", HttpStatus.OK);
//        }
//        else {
//            System.out.println("Error - create article");
//            return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
//        }
//    }


}
