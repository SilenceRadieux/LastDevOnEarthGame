package com.qgsoftware.lastdevonearth.backend.services;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class ArticleServiceModel {
    private long id;
    private Date dateCreation;
    private String cover;
    private String title;
    private String content;
    private List<VoteServiceModel> votes;
    private List<CommentaryServiceModel> comments;

    public ArticleServiceModel() {
    }

    public ArticleServiceModel(String cover, String title, String content, List<VoteServiceModel> votes,
                               List<CommentaryServiceModel> comments) {
        this.dateCreation = new Date();
        this.cover = cover;
        this.title = title;
        this.content = content;
        this.votes = votes;
        this.comments = comments;
    }

}
