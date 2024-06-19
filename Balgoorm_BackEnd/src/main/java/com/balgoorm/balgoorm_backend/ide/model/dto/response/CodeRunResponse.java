package com.balgoorm.balgoorm_backend.ide.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CodeRunResponse {
    String result;
    String answer;
    String errorMessage;
    String runTime;
    String memoryUsage;
    boolean isCorrect;

}
