package com.yogurt.domain.user.dto.common.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ChangeNameRequest {

    @NotEmpty(message = "이름은 필수 값입니다.")
    private String name;
}
