package com.qgsoftware.lastdevonearth.backend.controllers;

import com.qgsoftware.lastdevonearth.backend.dto.CommentaryDTO;
import com.qgsoftware.lastdevonearth.backend.services.CommentaryService;
import com.qgsoftware.lastdevonearth.backend.utils.CommentaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("commentary")
public class CommentaryController {

    CommentaryMapper commentaryMapper = CommentaryMapper.INSTANCE;

    private final CommentaryService commentaryService;

    @Autowired
    public CommentaryController(CommentaryService commentaryService) {this.commentaryService = commentaryService;}

    @PostMapping
    public void addCommentary(@RequestBody CommentaryDTO commentaryDTO) {
        commentaryService.add(commentaryMapper.commentaryDTOToCommentaryServiceModel(commentaryDTO), null);
    }

    @GetMapping
    public List<CommentaryDTO> findAll() {
        return commentaryMapper.listCommentaryServiceModelToCommentaryDTO(commentaryService.findAll());
    }

    @PutMapping("/{id}")
    public void updateCommentary(@PathVariable("id") Long id, @RequestBody CommentaryDTO commentaryDTO) {
        commentaryService.add(commentaryMapper.commentaryDTOToCommentaryServiceModel(commentaryDTO), id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteCommentary(@PathVariable("id") Long id) {return commentaryService.delete(id);}

}
