package com.balgoorm.balgoorm_backend.board.repository;

import com.balgoorm.balgoorm_backend.board.model.entity.BoardImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardImageRepository extends JpaRepository<BoardImage, Long> {
}
