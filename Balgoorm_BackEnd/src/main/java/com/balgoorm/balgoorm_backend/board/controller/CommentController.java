package com.balgoorm.balgoorm_backend.board.controller;

import com.balgoorm.balgoorm_backend.board.model.dto.request.CommentRequestDTO;
import com.balgoorm.balgoorm_backend.board.model.dto.response.CommentResponseDTO;
import com.balgoorm.balgoorm_backend.board.service.CommentService;
import com.balgoorm.balgoorm_backend.user.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{id}/comment")
    public CommentResponseDTO writeComment(@PathVariable Long id, @RequestBody CommentRequestDTO commentRequestDTO, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return commentService.writeComment(commentRequestDTO, id, userDetails.getUsername());
    }

    @PutMapping("/{id}/comment/{commentId}")
    public CommentResponseDTO updateComment(@PathVariable Long id, @PathVariable Long commentId, @RequestBody CommentRequestDTO commentRequestDTO) {
        return commentService.updateComment(commentRequestDTO, commentId);
    }

    @DeleteMapping("/{id}/comment/{commentId}")
    public String deleteComment(@PathVariable Long id, @PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return "Comment deleted successfully.";
    }
}
