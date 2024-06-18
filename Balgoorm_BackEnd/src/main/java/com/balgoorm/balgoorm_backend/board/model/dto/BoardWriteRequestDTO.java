package com.balgoorm.balgoorm_backend.board.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class BoardWriteRequestDTO {
    private String boardTitle;
    private String boardContent;

}
