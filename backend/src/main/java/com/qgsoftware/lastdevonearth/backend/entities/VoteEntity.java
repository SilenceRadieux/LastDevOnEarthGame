package com.qgsoftware.lastdevonearth.backend.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "vote")
public class VoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private ArticleEntity article;

    @Enumerated(EnumType.STRING)
    @Column(name = "vote", nullable = false)
    private Vote vote;

    public enum Vote {
        UPVOTE,
        DOWNVOTE
    }

}
