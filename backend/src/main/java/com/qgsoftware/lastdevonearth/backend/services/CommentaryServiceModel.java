package com.qgsoftware.lastdevonearth.backend.services;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CommentaryServiceModel {
    private Long id;
    private String username;
    private String content;
    private Date dateCreation;
}
