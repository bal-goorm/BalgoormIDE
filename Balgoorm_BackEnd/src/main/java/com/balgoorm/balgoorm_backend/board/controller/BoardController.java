package com.balgoorm.balgoorm_backend.board.controller;

import com.balgoorm.balgoorm_backend.board.model.dto.request.BoardEditRequest;
import com.balgoorm.balgoorm_backend.board.model.dto.request.BoardImageUploadDTO;
import com.balgoorm.balgoorm_backend.board.model.dto.request.BoardWriteRequestDTO;
import com.balgoorm.balgoorm_backend.board.model.dto.response.BoardResponseDTO;
import com.balgoorm.balgoorm_backend.board.service.BoardService;
import com.balgoorm.balgoorm_backend.user.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/{id}")
    public BoardResponseDTO searchBoard(@PathVariable("id") Long boardId) {
        return boardService.searchBoard(boardId);
    }

    @GetMapping
    public List<BoardResponseDTO> searchBoardList(
            @RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize,
            @RequestParam("direction") Sort.Direction direction) {
        return boardService.searchBoardList(page, pageSize, direction.name());
    }

    @PostMapping
    public BoardResponseDTO createBoard(BoardWriteRequestDTO boardWriteRequestDTO,
                                        @ModelAttribute BoardImageUploadDTO boardImageUploadDTO,
                                        Authentication authentication) throws IOException {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getUserId();
        Long boardId = boardService.saveBoard(boardWriteRequestDTO, boardImageUploadDTO, userId);
        return boardService.searchBoard(boardId);
    }

    @PutMapping("/{id}")
    public BoardResponseDTO editBoard(@PathVariable("id") Long boardId,
                                      @ModelAttribute BoardEditRequest boardEditRequest,
                                      Authentication authentication) throws IOException {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getUserId();
        return boardService.editBoard(boardId, boardEditRequest, userId);
    }

    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable("id") Long boardId, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getUserId();
        boardService.deleteBoard(boardId, userId);
    }

    @PostMapping("/{id}/like")
    public String likeBoard(@PathVariable("id") Long boardId, Authentication authentication) {
        try {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            Long userId = userDetails.getUserId();
            boardService.likeBoard(boardId, userId);
            return "Liked the board";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    @PostMapping("/{id}/unlike")
    public String unlikeBoard(@PathVariable("id") Long boardId, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getUserId();
        boardService.unlikeBoard(boardId, userId);
        return "Unliked the board";
    }
}
