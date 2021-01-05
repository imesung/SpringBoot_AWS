package com.mesung.naver;

public interface ArticlesService {
    public Article getArticle(Long articleId);
    public void removeArticle(Long articleId);
    public void saveArticle(Article article);
    public Article updateLikes(Long articleId, int likes);
}
