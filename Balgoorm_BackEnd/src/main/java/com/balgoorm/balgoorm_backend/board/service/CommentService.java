package com.balgoorm.balgoorm_backend.board.service;

import com.balgoorm.balgoorm_backend.board.model.dto.request.CommentRequestDTO;
import com.balgoorm.balgoorm_backend.board.model.dto.response.CommentResponseDTO;
import com.balgoorm.balgoorm_backend.board.model.entity.Board;
import com.balgoorm.balgoorm_backend.board.model.entity.Comment;
import com.balgoorm.balgoorm_backend.board.repository.BoardRepository;
import com.balgoorm.balgoorm_backend.board.repository.CommentRepository;
import com.balgoorm.balgoorm_backend.user.model.entity.User;
import com.balgoorm.balgoorm_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public CommentResponseDTO writeComment(CommentRequestDTO commentRequestDTO, Long boardId, String username) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("Invalid board Id"));
        User user = userRepository.findByUserId(username).orElseThrow(() -> new IllegalArgumentException("Invalid user Id"));

        Comment comment = Comment.builder()
                .commentContent(commentRequestDTO.getCommentContent())
                .user(user)
                .board(board)
                .build();

        commentRepository.save(comment);
        return new CommentResponseDTO(comment);
    }

    @Transactional
    public CommentResponseDTO updateComment(CommentRequestDTO commentRequestDTO, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("Invalid comment Id"));
        comment.setCommentContent(commentRequestDTO.getCommentContent());
        commentRepository.save(comment);
        return new CommentResponseDTO(comment);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("Invalid comment Id"));
        commentRepository.delete(comment);
    }
}
