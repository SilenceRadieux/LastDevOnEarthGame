package com.qgsoftware.lastdevonearth.backend.utils;


import com.qgsoftware.lastdevonearth.backend.dto.ArticleDTO;
import com.qgsoftware.lastdevonearth.backend.dto.UserDTO;
import com.qgsoftware.lastdevonearth.backend.services.ArticleServiceModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.userdetails.UserDetails;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userDetailsToUserDTO(UserDetails userDetails);

    ArticleDTO articleServiceModelToArticleDTO(ArticleServiceModel articleServiceModel);
}
