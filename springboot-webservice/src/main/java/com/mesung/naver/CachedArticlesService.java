package com.mesung.naver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
class CachingArticlesService implements ArticlesService {

    @Autowired
    private ArticlesRepository articlesRepository;

    @Override
    @Cacheable(value = "articles", key = "#articleId")
    public Article getArticle(Long articleId) {
        articlesRepository.get(articleId);
        return null;
    }

    @Override
    @CacheEvict(value = "articles", key = "#articleId")
    public void removeArticle(Long articleId) {
        articlesRepository.remove(articleId);
    }

    @Override
    public void saveArticle(Article article) {
        articlesRepository.save(article);
    }

    @Override
    @CachePut(value = "articles", key = "#articleId")
    public Article updateLikes(Long articleId, int likes) {
        return articlesRepository.updateLikes(articleId, likes);
    }
}
