package com.qgsoftware.lastdevonearth.backend.repositories;

import com.qgsoftware.lastdevonearth.backend.entities.ArticleEntity;
import com.qgsoftware.lastdevonearth.backend.entities.VoteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends CrudRepository<VoteEntity, Long> {
    Long countByArticleId(Long articleId);
}
