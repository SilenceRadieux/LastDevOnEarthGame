package com.qgsoftware.lastdevonearth.backend.dto;

import java.util.Date;

public record CommentaryDTO(long id, String username, String content, Date dateCreation) {
}
