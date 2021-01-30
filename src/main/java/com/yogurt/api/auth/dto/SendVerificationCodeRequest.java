package com.yogurt.api.auth.dto;

import com.yogurt.validation.annotation.EmailValid;
import com.yogurt.validation.annotation.VerificationValid;
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