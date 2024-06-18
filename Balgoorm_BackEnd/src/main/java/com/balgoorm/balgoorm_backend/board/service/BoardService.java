package com.balgoorm.balgoorm_backend.board.service;

import com.balgoorm.balgoorm_backend.board.model.dto.request.BoardImageUploadDTO;
import com.balgoorm.balgoorm_backend.board.model.dto.request.BoardWriteRequestDTO;
import com.balgoorm.balgoorm_backend.board.model.dto.request.BoardEditRequest;
import com.balgoorm.balgoorm_backend.board.model.dto.response.BoardResponseDTO;
import com.balgoorm.balgoorm_backend.board.model.entity.Board;
import com.balgoorm.balgoorm_backend.board.model.entity.BoardImage;
import com.balgoorm.balgoorm_backend.board.repository.BoardRepository;
import com.balgoorm.balgoorm_backend.board.repository.BoardImageRepository;
import com.balgoorm.balgoorm_backend.user.model.entity.User;
import com.balgoorm.balgoorm_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardImageRepository boardImageRepository;
    private final UserRepository userRepository;

    @Value("${file.boardImagePath}")
    private String uploadFolder;

    @Transactional(readOnly = true)
    public Board getBoardById(Long boardId) {
        return boardRepository.findById(boardId).orElse(null);
    }

    @Transactional(readOnly = true)
    public BoardResponseDTO searchBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("Board not found with id: " + boardId));
        return new BoardResponseDTO(board);
    }

    @Transactional(readOnly = true)
    public List<BoardResponseDTO> searchBoardList(int page, int pageSize, String direction) {
        return boardRepository.findAll(PageRequest.of(page, pageSize, Sort.by(Sort.Direction.fromString(direction), "boardCreateDate")))
                .stream()
                .map(BoardResponseDTO::new)
                .collect(Collectors.toList());
    }


    @Transactional
    public Long saveBoard(BoardWriteRequestDTO boardWriteRequestDTO, BoardImageUploadDTO boardImageUploadDTO, String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("사용자 ID에 해당하는 회원이 없습니다."));

        Board board = Board.builder()
                .boardTitle(boardWriteRequestDTO.getBoardTitle())
                .boardContent(boardWriteRequestDTO.getBoardContent())
                .boardCreateDate(LocalDateTime.now())
                .user(user)
                .build();

        boardRepository.save(board);

        if (boardImageUploadDTO.getFiles() != null && !boardImageUploadDTO.getFiles().isEmpty()) {
            saveBoardImages(boardImageUploadDTO.getFiles(), board);
        }

        return board.getBoardId();
    }

    private void saveBoardImages(List<MultipartFile> files, Board board) {
        if (board.getBoardImages() == null) {
            board.setBoardImages(new ArrayList<>());
        }
        for (MultipartFile file : files) {
            UUID uuid = UUID.randomUUID();
            String imageFileName = uuid + "_" + file.getOriginalFilename();

            File destinationFile = new File(uploadFolder + File.separator + imageFileName);

            log.info("파일 업로드 경로: {}", destinationFile.getAbsolutePath());

            try {
                file.transferTo(destinationFile);
            } catch (IOException e) {
                log.error("파일 업로드 중 오류 발생: {}", e.getMessage());
                throw new RuntimeException("파일 업로드 중 오류가 발생했습니다.", e);
            }

            BoardImage image = BoardImage.builder()
                    .url("/uploads/" + imageFileName)
                    .board(board)
                    .build();

            board.getBoardImages().add(image);
            boardImageRepository.save(image);
        }
    }


@Transactional
    public BoardResponseDTO editBoard(Long boardId, BoardEditRequest boardEditRequest, String userId) throws IOException {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        if (!board.getUser().getUserId().equals(userId)) {
            throw new IllegalArgumentException("다른 사용자의 게시글은 수정할 수 없습니다.");
        }
        board.setBoardTitle(boardEditRequest.getBoardTitle());
        board.setBoardContent(boardEditRequest.getBoardContent());

        // 필요한 경우 파일 처리 로직 추가

        boardRepository.save(board);
        return new BoardResponseDTO(board);
    }

    @Transactional
    public void deleteBoard(Long boardId, String userId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        if (!board.getUser().getUserId().equals(userId)) {
            throw new IllegalArgumentException("다른 사용자의 게시글은 삭제할 수 없습니다.");
        }
        boardRepository.deleteById(boardId);
    }
}
