package com.qgsoftware.lastdevonearth.backend.services;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.Optional;

@Data
@AllArgsConstructor
public class ArticleServiceModel {
    private long id;
    private Date dateCreation;
    private String cover;
    private String title;
    private String content;

    public ArticleServiceModel() {}

    public ArticleServiceModel(String cover, String title, String content) {
        this.dateCreation = new Date();
        this.cover = cover;
        this.title = title;
        this.content = content;
    }

}
