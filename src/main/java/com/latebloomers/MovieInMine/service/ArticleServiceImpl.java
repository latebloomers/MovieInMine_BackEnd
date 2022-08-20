package com.latebloomers.MovieInMine.service;

import com.latebloomers.MovieInMine.model.ArticleDto;
import com.latebloomers.MovieInMine.model.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService{


    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<ArticleDto> getArticles() throws SQLException {
        return articleMapper.selectArticle();
    }

    @Override
    public ArticleDto getArticle(int articleId) throws SQLException {
        return articleMapper.selectArticleByNo(articleId);
    }

    @Override
    public boolean createArticle(ArticleDto article) throws SQLException {
        return articleMapper.insertArticle(article) == 1;
    }

    @Override
    public boolean updateArticle(ArticleDto article) throws SQLException {
        return articleMapper.updateArticle(article) == 1;
    }

    @Override
    public boolean deleteArticle(int articleId) throws SQLException {
        return articleMapper.deleteArticle(articleId) == 1;
    }


}
