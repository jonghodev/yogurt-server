package com.yogurt.api.auth.dto;

import com.yogurt.api.studio.domain.Studio;
import com.yogurt.api.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginResponse {

    private String accessToken;
    private User user;
    private Studio studio;

    public static LoginResponse of(String jwtToken, User user, Studio studio) {
        return new LoginResponse(jwtToken, user, studio);
    }
}
