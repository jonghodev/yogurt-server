package com.yogurt.domain.auth.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class SocialSignupRequest {

    @NotEmpty(message = "accessToken은 필수 값입니다.")
    private String accessToken;
}
