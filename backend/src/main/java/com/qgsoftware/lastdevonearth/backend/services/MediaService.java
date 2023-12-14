package com.qgsoftware.lastdevonearth.backend.services;

import com.qgsoftware.lastdevonearth.backend.entities.MediaEntity;
import com.qgsoftware.lastdevonearth.backend.repositories.MediaRepository;
import com.qgsoftware.lastdevonearth.backend.utils.MediaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaService {

    private final MediaRepository mediaRepository;
    MediaMapper mediaMapper = MediaMapper.INSTANCE;

    @Autowired
    public MediaService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    public void add(MediaServiceModel mediaServiceModel, @Nullable Long id) {
        MediaEntity mediaEntity = mediaMapper.mediaServiceModelToMediaEntity(mediaServiceModel);
        if (id != null) {
            mediaEntity.setId(id);
        }
        mediaRepository.save(mediaEntity);
    }

    public List<MediaServiceModel> findAll() {
        return mediaMapper.listMediaEntityToListMediaServiceModel((List<MediaEntity>) mediaRepository.findAll());
    }

    public boolean delete(long id) {
        try {
            mediaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
