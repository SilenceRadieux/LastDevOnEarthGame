package com.qgsoftware.lastdevonearth.backend.services;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentaryServiceModel {
    private Long id;
    private Long idUser;
    private String content;
    private String dateTime;
}
