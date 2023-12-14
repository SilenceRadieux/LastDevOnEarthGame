package com.qgsoftware.lastdevonearth.backend.services;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ArticleServiceModel {
    private long id;
    private Date dateCreation;
    private String cover;
    private String title;
    private String content;
    private int upvotes;
    private int downvotes;

    public ArticleServiceModel() {
    }

    public ArticleServiceModel(String cover, String title, String content, int upvotes, int downvotes) {
        this.dateCreation = new Date();
        this.cover = cover;
        this.title = title;
        this.content = content;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
    }

}
