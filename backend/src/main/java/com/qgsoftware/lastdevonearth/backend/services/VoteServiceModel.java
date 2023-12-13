package com.qgsoftware.lastdevonearth.backend.services;

import com.qgsoftware.lastdevonearth.backend.entities.VoteEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VoteServiceModel {
    private Long id;
    private Long idUser;
    private Long articleId;
    VoteEntity.Vote vote;
}
