package com.qgsoftware.lastdevonearth.backend.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "article")
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "date_creation", nullable = false)
    private Date dateCreation;

    @Column(name = "cover", nullable = false)
    private String cover;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<VoteEntity> votes;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<CommentaryEntity> comments;

}
