package com.yogurt.domain.user.dto.common.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ChangeProfileUrlRequest {

    @NotEmpty(message = "프로필은 필수 값입니다.")
    private String profileUrl;
}
