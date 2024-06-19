package com.balgoorm.balgoorm_backend.board.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardWriteRequestDTO {
    private String boardTitle;
    private String boardContent;

}
