package com.qgsoftware.lastdevonearth.backend.controllers;

import com.qgsoftware.lastdevonearth.backend.dto.ArticleDTO;
import com.qgsoftware.lastdevonearth.backend.dto.VoteDTO;
import com.qgsoftware.lastdevonearth.backend.entities.ArticleEntity;
import com.qgsoftware.lastdevonearth.backend.repositories.ArticleRepository;
import com.qgsoftware.lastdevonearth.backend.services.ArticleService;
import com.qgsoftware.lastdevonearth.backend.utils.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("article")
public class ArticleController {

    private final ArticleService articleService;
    ArticleMapper articleMapper = ArticleMapper.INSTANCE;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public void addArticle(@RequestBody ArticleDTO articleDTO) {
        articleService.add(articleMapper.articleDTOToArticleServiceModel(articleDTO), null);
    }

    @GetMapping
    public List<ArticleDTO> findAll() {
        return articleMapper.listArticleServiceModelToArticleDTO(articleService.findAll());
    }

    @GetMapping("/{id}")
    public ArticleDTO findById(@PathVariable("id") Long id) {
        return articleMapper.articleServiceModelToArticleDTO(articleService.findById(id));
    }

    @PutMapping("/{id}")
    public void updateArticle(@PathVariable("id") Long id, @RequestBody ArticleDTO articleDTO) {
        articleService.add(articleMapper.articleDTOToArticleServiceModel(articleDTO), id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteArticle(@PathVariable("id") Long id) {
        return articleService.delete(id);
    }

}
