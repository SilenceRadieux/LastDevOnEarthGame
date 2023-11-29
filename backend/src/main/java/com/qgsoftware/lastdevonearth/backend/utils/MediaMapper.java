package com.qgsoftware.lastdevonearth.backend.utils;

import com.qgsoftware.lastdevonearth.backend.dto.MediaDTO;
import com.qgsoftware.lastdevonearth.backend.entities.MediaEntity;
import com.qgsoftware.lastdevonearth.backend.services.MediaServiceModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MediaMapper {

    MediaMapper INSTANCE = Mappers.getMapper(MediaMapper.class);

    MediaServiceModel mediaDTOToMediaServiceModel(MediaDTO mediaDTO);

    MediaEntity mediaServiceModelToMediaEntity(MediaServiceModel mediaServiceModel);

    MediaServiceModel mediaEntityToMediaServiceModel(MediaEntity mediaEntity);

    MediaDTO mediaServiceModelToMediaDTO(MediaServiceModel mediaServiceModel);

    List<MediaServiceModel> listMediaEntityToListMediaServiceModel(List<MediaEntity> listMediaEntity);

    List<MediaDTO> listMediaServiceModelToMediaDTO(List<MediaServiceModel> mediaServiceModel);

}
