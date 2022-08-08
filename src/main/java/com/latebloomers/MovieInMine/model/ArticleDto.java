package com.latebloomers.MovieInMine.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter @Setter
public class ArticleDto {

    private int articleId;
    private int userId;

    @Size(min=1, max=20, message="제목은 적어도 1글자 이상 입력 해야합니다.")
    private String title;

    @Size(min = 1, max = 200, message = "내용은 1글자 이상, 200글자 이하여야 합니다.")
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
