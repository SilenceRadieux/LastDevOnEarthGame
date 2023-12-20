package com.qgsoftware.lastdevonearth.backend.controllers;

import com.qgsoftware.lastdevonearth.backend.dto.CommentaryDTO;
import com.qgsoftware.lastdevonearth.backend.entities.CommentaryEntity;
import com.qgsoftware.lastdevonearth.backend.repositories.CommentaryRepository;
import com.qgsoftware.lastdevonearth.backend.services.CommentaryService;
import com.qgsoftware.lastdevonearth.backend.utils.CommentaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("commentary")
public class CommentaryController {

    private final CommentaryService commentaryService;
    private final CommentaryRepository commentaryRepository;
    CommentaryMapper commentaryMapper = CommentaryMapper.INSTANCE;

    @Autowired
    public CommentaryController(CommentaryService commentaryService, CommentaryRepository commentaryRepository) {
        this.commentaryService = commentaryService;
        this.commentaryRepository = commentaryRepository;
    }


    @GetMapping("/{id}")
    public CommentaryEntity findById(@PathVariable("id") Long id) {
        return commentaryRepository.findById(id).get();
    }

    @GetMapping
    public List<CommentaryDTO> findAll() {
        return commentaryMapper.listCommentaryServiceModelToCommentaryDTO(commentaryService.findAll());
    }


    @DeleteMapping("/{id}")
    public boolean deleteCommentary(@PathVariable("id") Long id) {
        return commentaryService.delete(id);
    }

}
