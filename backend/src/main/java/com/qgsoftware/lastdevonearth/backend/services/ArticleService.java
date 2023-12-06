package com.qgsoftware.lastdevonearth.backend.services;

import com.qgsoftware.lastdevonearth.backend.entities.ArticleEntity;
import com.qgsoftware.lastdevonearth.backend.repositories.ArticleRepository;
import com.qgsoftware.lastdevonearth.backend.utils.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    ArticleMapper articleMapper = ArticleMapper.INSTANCE;


   private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void add(ArticleServiceModel articleServiceModel, @Nullable Long id) {
        ArticleEntity articleEntity = articleMapper.articleServiceModelToArticleEntity(articleServiceModel);
        if (id != null) {
            articleEntity.setId(id);
        }
        articleRepository.save(articleEntity);
    }

    public List<ArticleServiceModel> findAll() {
        return articleMapper.listArticleEntityToListArticleServiceModel((List<ArticleEntity>)
                articleRepository.findAll());
    }

    public boolean delete(long id) {
        try {
            articleRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
