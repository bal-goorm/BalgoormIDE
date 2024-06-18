package com.balgoorm.balgoorm_backend.board.model.entity;

import com.balgoorm.balgoorm_backend.user.model.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    private String commentContent;
    private LocalDateTime commentCreateDate;
    private int likesCount;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID", nullable = false)
    private Board board;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Likes> likes = new ArrayList<>(); // 좋아요 리스트

    @Builder
    public Comment(String commentContent, LocalDateTime commentCreateDate, User user, Board board) {
        this.commentContent = commentContent;
        this.commentCreateDate = commentCreateDate; // 초기값 설정
        this.user = user;
        this.board = board;
        this.likesCount = 0; // 기본값으로 좋아요 수를 0으로 설정
    }

    public int getLikeCount() {
        return likesCount;
    }

    public void incrementLikes() {
        this.likesCount++;
    }

    public void decrementLikes() {
        this.likesCount--;
    }
}
