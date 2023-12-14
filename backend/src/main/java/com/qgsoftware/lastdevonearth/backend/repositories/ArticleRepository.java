package com.qgsoftware.lastdevonearth.backend.repositories;

import com.qgsoftware.lastdevonearth.backend.entities.ArticleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends CrudRepository<ArticleEntity, Long> {
}
