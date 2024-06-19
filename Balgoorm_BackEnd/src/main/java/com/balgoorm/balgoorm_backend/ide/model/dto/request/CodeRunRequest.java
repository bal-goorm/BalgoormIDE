package com.balgoorm.balgoorm_backend.ide.model.dto.request;

import com.balgoorm.balgoorm_backend.ide.model.enums.LanguageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CodeRunRequest {

    Long quizId;
    Long userId;
    LanguageType language;
    String code;

}
