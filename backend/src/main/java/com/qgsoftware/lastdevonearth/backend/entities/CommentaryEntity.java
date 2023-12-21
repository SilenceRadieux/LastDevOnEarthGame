package com.qgsoftware.lastdevonearth.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "commentary")
public class CommentaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "article_id")
    @JsonIgnore
    private ArticleEntity article;

    @Column(name = "content")
    private String content;

    @Column(name = "date_time")
    private Date dateCreation;

}
