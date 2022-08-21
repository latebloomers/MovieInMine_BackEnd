package com.latebloomers.MovieInMine.controller;

import com.latebloomers.MovieInMine.model.ArticleDto;
import com.latebloomers.MovieInMine.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@Slf4j
@RestController
@RequestMapping("/article")
public class ArticleController {
    //WEBHook Test

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private ArticleService articleService;

    @GetMapping()
    public ResponseEntity<List<ArticleDto>> getArticles() throws SQLException {
        List<ArticleDto> articles = articleService.getArticles();

        logger.info("ArticleController.getArticles");
        logger.info(articles.toString());

        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("{articleId}")
    public ResponseEntity<ArticleDto> getArticle(@PathVariable("articleId") int articleId) throws SQLException {
        ArticleDto article = articleService.getArticle(articleId);

        logger.info("ArticleController.getArticle");
        logger.info(article.toString());

        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity createArticle(@RequestBody @Validated ArticleDto articleDto, BindingResult bindingResult) throws SQLException {
        logger.info("ArticleController.createArticle");

        if (bindingResult.hasErrors()){

            Map<String, String> error = new HashMap<>();

            bindingResult
                    .getFieldErrors()
                    .forEach(f -> {
                        log.info("검증 오류 발생 : " + f.getField() + ": " + f.getDefaultMessage());
                        error.put(f.getField(), f.getDefaultMessage());
                    });
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        if (articleService.createArticle(articleDto)) {
            logger.info(SUCCESS);
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        logger.info(FAIL);
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }

    @PutMapping("{articleId}")
    public ResponseEntity updateArticle(@RequestBody @Validated ArticleDto article,
                                                BindingResult bindingResult) throws SQLException{
        logger.info("ArticleController.updateArticle");

        if (bindingResult.hasErrors()) {

            Map<String, String> error = new HashMap<>();

            bindingResult
                    .getFieldErrors()
                    .forEach( f -> {
                        log.info("검증 오류 발생 : " + f.getField() + ": " + f.getDefaultMessage());
                        error.put(f.getField(), f.getDefaultMessage());
                    } );
            
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        if(articleService.updateArticle(article)) {
            logger.info(SUCCESS);
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        logger.info(FAIL);
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{articleId}")
    public ResponseEntity<String> deleteArticle(@PathVariable int articleId) throws SQLException {
        logger.info("ArticleController.deleteArticle");

        if(articleService.deleteArticle(articleId)){
            logger.info(SUCCESS);
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        logger.info(FAIL);
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
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
