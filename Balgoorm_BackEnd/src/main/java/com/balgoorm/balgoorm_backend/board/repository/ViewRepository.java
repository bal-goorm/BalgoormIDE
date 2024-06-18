package com.balgoorm.balgoorm_backend.board.repository;

import com.balgoorm.balgoorm_backend.board.model.entity.View;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ViewRepository extends JpaRepository<View, Long> {
    boolean existsByUserIdAndBoardBoardId(Long userId, Long boardId);
}
