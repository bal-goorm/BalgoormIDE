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
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;
    private String boardTitle;
    private String boardContent;
    private LocalDateTime boardCreateDate;
    private int likesCount; // 좋아요 수 필드 추가

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @OrderBy("id asc")
    private List<BoardImage> boardImages;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @OrderBy("commentCreateDate asc")
    private List<Comment> comments = new ArrayList<>(); // 빈 리스트로 초기화

    @Builder
    public Board(String boardTitle, String boardContent, LocalDateTime boardCreateDate, User user) {
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardCreateDate = boardCreateDate;
        this.user = user;
        this.likesCount = 0; // 기본값으로 좋아요 수를 0으로 설정
    }

    public void update(String boardTitle, String boardContent) {
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
    }

    public void incrementLikes() {
        this.likesCount++;
    }

    public void decrementLikes() {
        this.likesCount--;
    }
}
