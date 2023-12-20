package com.qgsoftware.lastdevonearth.backend.repositories;

import com.qgsoftware.lastdevonearth.backend.entities.ArticleEntity;
import com.qgsoftware.lastdevonearth.backend.entities.CommentaryEntity;
import com.qgsoftware.lastdevonearth.backend.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CommentaryRepository extends CrudRepository<CommentaryEntity, Long> {

    Optional<CommentaryEntity> findByUserAndArticle(UserEntity userEntity, ArticleEntity articleEntity);
}
