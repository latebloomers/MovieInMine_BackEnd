package com.example.sideproject.model.mapper;

import com.example.sideproject.model.ArticleDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface ArticleMapper {

    public ArticleDto selectArticleByNo(int articleId);

    public List<ArticleDto> selectArticle() throws SQLException;

    public int insertArticle(ArticleDto article) throws SQLException;

    public int updateArticle(ArticleDto article) throws SQLException;

    public int deleteArticle(int articleId) throws SQLException;
}
