package com.balgoorm.balgoorm_backend.user.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupRequest {

    private String userId;


    private String password;


    private String nickname;
}
