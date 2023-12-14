package com.qgsoftware.lastdevonearth.backend.dto;

public record VoteDTO(Long id, Long userId, Long articleId, String vote) {
}
