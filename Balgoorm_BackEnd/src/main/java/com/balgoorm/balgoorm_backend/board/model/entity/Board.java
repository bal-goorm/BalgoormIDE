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
    private int likesCount;
    private int viewCount;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @OrderBy("id asc")
    private List<BoardImage> boardImages;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @OrderBy("commentCreateDate asc")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<View> views = new ArrayList<>();

    @Builder
    public Board(String boardTitle, String boardContent, LocalDateTime boardCreateDate, User user) {
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardCreateDate = boardCreateDate != null ? boardCreateDate : LocalDateTime.now();
        this.user = user;
        this.likesCount = 0;
        this.viewCount = 0;
    }

    public void incrementLikes() {
        this.likesCount++;
    }

    public void decrementLikes() {
        this.likesCount--;
    }

    public void incrementViews() {
        this.viewCount++;
    }
}
