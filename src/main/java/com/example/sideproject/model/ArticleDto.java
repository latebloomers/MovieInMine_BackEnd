package com.example.sideproject.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ArticleDto {

    private int articleId;
    private int userId;
    private String title;
    private String content;
    private String createdAt;

    public ArticleDto() {
    }

    public ArticleDto(int id, int userId, String title, String content, String createdAt) {
        this.articleId = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "ArticleDto{" +
                "id=" + articleId +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
