package com.qgsoftware.lastdevonearth.backend.controllers;

import com.qgsoftware.lastdevonearth.backend.dto.MediaDTO;
import com.qgsoftware.lastdevonearth.backend.services.MediaService;
import com.qgsoftware.lastdevonearth.backend.utils.MediaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("media")
public class MediaController {

    MediaMapper mediaMapper = MediaMapper.INSTANCE;

    private final MediaService mediaService;

    @Autowired
    public MediaController(MediaService mediaService) {this.mediaService = mediaService;}

    @PostMapping
    public void addMedia(@RequestBody MediaDTO mediaDTO) {
        mediaService.add(mediaMapper.mediaDTOToMediaServiceModel(mediaDTO), null);
    }

    @GetMapping
    public List<MediaDTO> findAll() {return mediaMapper.listMediaServiceModelToMediaDTO(mediaService.findAll());}

    @PutMapping("/{id}")
    public void updateMedia(@PathVariable("id") Long id, @RequestBody MediaDTO mediaDTO) {
        mediaService.add(mediaMapper.mediaDTOToMediaServiceModel(mediaDTO), id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteMedia(@PathVariable("id") Long id) {return mediaService.delete(id);}

}
