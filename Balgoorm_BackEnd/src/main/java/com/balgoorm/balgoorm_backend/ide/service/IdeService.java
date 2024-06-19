package com.balgoorm.balgoorm_backend.ide.service;

import com.balgoorm.balgoorm_backend.ide.model.dto.request.CodeRunRequest;
import com.balgoorm.balgoorm_backend.ide.model.dto.response.CodeRunResponse;

import java.io.IOException;

public interface IdeService {
    CodeRunResponse codeRun(CodeRunRequest executeRequest) throws IOException, InterruptedException;
}
