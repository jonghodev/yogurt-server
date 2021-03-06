package com.yogurt.domain.article.domain;

import com.yogurt.domain.base.entity.BaseEntity;
import com.yogurt.domain.user.domain.User;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Article extends BaseEntity {

    @Column(nullable = false)
    private Long studioId;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private int viewCount;

    @Column(nullable = false)
    private int likeCount;
}
