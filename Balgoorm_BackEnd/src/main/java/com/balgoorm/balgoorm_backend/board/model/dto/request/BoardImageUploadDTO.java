package com.balgoorm.balgoorm_backend.board.model.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class BoardImageUploadDTO {

    private List<MultipartFile> files;
}