package com.qgsoftware.lastdevonearth.backend.dto;

import com.qgsoftware.lastdevonearth.backend.entities.VoteEntity;

public record VoteDTO(Long id, Long userId, Long articleId, VoteEntity.Vote vote) {
}
