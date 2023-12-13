package com.qgsoftware.lastdevonearth.backend.repositories;

import com.qgsoftware.lastdevonearth.backend.entities.ArticleEntity;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends CrudRepository<ArticleEntity, Long> {
}
