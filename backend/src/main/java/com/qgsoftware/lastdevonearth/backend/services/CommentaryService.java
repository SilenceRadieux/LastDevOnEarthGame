package com.qgsoftware.lastdevonearth.backend.services;

import com.qgsoftware.lastdevonearth.backend.entities.CommentaryEntity;
import com.qgsoftware.lastdevonearth.backend.repositories.CommentaryRepository;
import com.qgsoftware.lastdevonearth.backend.utils.CommentaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentaryService {

    private final CommentaryRepository commentaryRepository;
    CommentaryMapper commentaryMapper = CommentaryMapper.INSTANCE;

    @Autowired
    public CommentaryService(CommentaryRepository commentaryRepository) {
        this.commentaryRepository = commentaryRepository;
    }

    public CommentaryServiceModel findById(Long id) {
        Optional<CommentaryEntity> commentaryEntity = commentaryRepository.findById(id);
        if (commentaryEntity.isPresent()) {
            return commentaryMapper.commentaryEntityToCommentaryServiceModel(commentaryEntity.get());
        }
        return null;
    }

    public List<CommentaryServiceModel> findAll() {
        return commentaryMapper.listCommentaryEntityToListCommentaryServiceModel(
                (List<CommentaryEntity>) commentaryRepository.findAll());
    }

    public boolean delete(long id) {
        try {
            commentaryRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
