package com.balgoorm.balgoorm_backend.board.controller;

import com.balgoorm.balgoorm_backend.board.model.dto.BoardEditRequest;
import com.balgoorm.balgoorm_backend.board.model.dto.BoardImageUploadDTO;
import com.balgoorm.balgoorm_backend.board.model.dto.BoardWriteRequestDTO;
import com.balgoorm.balgoorm_backend.board.model.dto.BoardResponseDTO;
import com.balgoorm.balgoorm_backend.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Long boardId = boardService.saveBoard(boardWriteRequestDTO, boardImageUploadDTO, userDetails.getUsername());
        return boardService.searchBoard(boardId);
    }

    @PutMapping("/{id}")
    public BoardResponseDTO editBoard(@PathVariable("id") Long boardId,
                                      @ModelAttribute BoardEditRequest boardEditRequest,
                                      Authentication authentication) throws IOException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return boardService.editBoard(boardId, boardEditRequest, userDetails.getUsername());
    }

    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable("id") Long boardId, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        boardService.deleteBoard(boardId, userDetails.getUsername());
    }
}
