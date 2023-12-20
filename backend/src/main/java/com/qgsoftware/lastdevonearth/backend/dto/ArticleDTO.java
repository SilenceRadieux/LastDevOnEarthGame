package com.qgsoftware.lastdevonearth.backend.dto;

import java.util.Date;
import java.util.List;

public record ArticleDTO(
        long id,
        Date dateCreation,
        String cover,
        String title,
        String content,
        List<VoteDTO> votes,
        List<CommentaryDTO> comments
) {}
