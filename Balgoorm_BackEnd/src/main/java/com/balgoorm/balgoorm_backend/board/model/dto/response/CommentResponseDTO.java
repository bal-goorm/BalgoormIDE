package com.balgoorm.balgoorm_backend.board.model.dto.response;

import com.balgoorm.balgoorm_backend.board.model.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDTO {
    private Long commentId;
    private String commentContent;
    private LocalDateTime commentCreateDate;
    private String userId;
    private Long boardId;

    public CommentResponseDTO(Comment comment) {
        this.commentId = comment.getCommentId();
        this.commentContent = comment.getCommentContent();
        this.commentCreateDate = comment.getCommentCreateDate();
        this.userId = comment.getUser().getUserId();
        this.boardId = comment.getBoard().getBoardId();
    }
}
