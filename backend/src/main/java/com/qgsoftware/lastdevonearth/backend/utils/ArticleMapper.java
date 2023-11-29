package com.qgsoftware.lastdevonearth.backend.utils;

import com.qgsoftware.lastdevonearth.backend.dto.ArticleDTO;
import com.qgsoftware.lastdevonearth.backend.entities.ArticleEntity;
import com.qgsoftware.lastdevonearth.backend.services.ArticleServiceModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ArticleMapper {

    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    ArticleServiceModel articleDTOToArticleServiceModel(ArticleDTO articleDTO);

    ArticleEntity articleServiceModelToArticleEntity(ArticleServiceModel articleServiceModel);

    ArticleServiceModel articleEntityToArticleServiceModel(ArticleEntity articleEntity);

    ArticleDTO articleServiceModelToArticleDTO(ArticleServiceModel articleServiceModel);

    List<ArticleServiceModel> listArticleEntityToListArticleServiceModel(List<ArticleEntity> listArticleEntity);

    List<ArticleDTO> listArticleServiceModelToArticleDTO(List<ArticleServiceModel> articleServiceModel);

}
