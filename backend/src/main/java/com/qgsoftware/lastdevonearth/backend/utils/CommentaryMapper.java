package com.qgsoftware.lastdevonearth.backend.utils;

import com.qgsoftware.lastdevonearth.backend.dto.CommentaryDTO;
import com.qgsoftware.lastdevonearth.backend.entities.CommentaryEntity;
import com.qgsoftware.lastdevonearth.backend.services.CommentaryServiceModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CommentaryMapper {

    CommentaryMapper INSTANCE = Mappers.getMapper(CommentaryMapper.class);

    @Mapping(target = "username", source = "user.username")
    CommentaryServiceModel commentaryEntityToCommentaryServiceModel(CommentaryEntity commentaryEntity);

    CommentaryDTO commentaryServiceModelToCommentaryDTO(CommentaryServiceModel commentaryServiceModel);

    List<CommentaryServiceModel> listCommentaryEntityToListCommentaryServiceModel(
            List<CommentaryEntity> listCommentaryEntity
    );

    List<CommentaryDTO> listCommentaryServiceModelToCommentaryDTO(
            List<CommentaryServiceModel> listCommentaryServiceModel
    );

}
