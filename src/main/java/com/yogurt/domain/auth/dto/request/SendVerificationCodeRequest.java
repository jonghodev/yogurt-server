package com.yogurt.domain.auth.dto.request;

import com.yogurt.validation.annotation.EmailValid;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class SendVerificationCodeRequest {

    @EmailValid
    @NotEmpty(message = "이메일은 필수 값입니다.")
    private String email;
}
