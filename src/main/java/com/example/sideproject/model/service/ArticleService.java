package com.example.sideproject.model.service;

import com.example.sideproject.model.ArticleDto;

import java.sql.SQLException;
import java.util.List;

public interface ArticleService {

    public List<ArticleDto> getArticles() throws SQLException;
    public ArticleDto getArticle(int articleId) throws SQLException;
    public boolean createArticle(ArticleDto article) throws SQLException;
    public boolean updateArticle(ArticleDto article) throws SQLException;
    public boolean deleteArticle(int articleId) throws SQLException;
}
