package com.balgoorm.balgoorm_backend.board.repository;

import com.balgoorm.balgoorm_backend.board.model.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByUserIdAndBoardBoardId(Long userId, Long boardId);
    Optional<Likes> findByUserIdAndCommentCommentId(Long userId, Long commentId);
    boolean existsByUserIdAndBoardBoardId(Long userId, Long boardId);
    boolean existsByUserIdAndCommentCommentId(Long userId, Long commentId);
}
