package com.qgsoftware.lastdevonearth.backend.repositories;

import com.qgsoftware.lastdevonearth.backend.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);




}
