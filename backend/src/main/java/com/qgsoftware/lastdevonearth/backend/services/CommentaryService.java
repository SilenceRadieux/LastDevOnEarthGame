package com.qgsoftware.lastdevonearth.backend.services;

import com.qgsoftware.lastdevonearth.backend.entities.CommentaryEntity;
import com.qgsoftware.lastdevonearth.backend.repositories.CommentaryRepository;
import com.qgsoftware.lastdevonearth.backend.utils.CommentaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentaryService {

    CommentaryMapper commentaryMapper = CommentaryMapper.INSTANCE;

    private final CommentaryRepository commentaryRepository;

    @Autowired
    public CommentaryService(CommentaryRepository commentaryRepository) {
        this.commentaryRepository = commentaryRepository;
    }


    public void add(CommentaryServiceModel commentaryServiceModel, @Nullable Long id) {
        CommentaryEntity commentaryEntity = commentaryMapper.commentaryServiceModelToCommentaryEntity(
                commentaryServiceModel);
        if (id != null) {
            commentaryEntity.setId(id);
        }
        commentaryRepository.save(commentaryEntity);
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
