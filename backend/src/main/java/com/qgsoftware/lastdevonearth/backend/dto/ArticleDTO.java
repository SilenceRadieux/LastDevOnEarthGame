package com.qgsoftware.lastdevonearth.backend.dto;

import java.util.Date;

public record ArticleDTO(long id, Date dateCreation, String cover, String title, String content) {
}
