package com.yogurt.domain.base.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
@Getter
@Embeddable
public class Email {

    public static final String PATTERN = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
    public static final String PATTERN_MESSAGE = "이메일의 형식을 맞춰주세요.";

    @JsonIgnore
    @Transient
    private final Pattern pattern = Pattern.compile(PATTERN);

    @Column(nullable = false, unique = true)
    private String email;

    private Email(String email) {
        validate(email);
        this.email = email;
    }

    private void validate(String email) {
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
//            throw new YogurtInvalidFormatException(PATTERN_MESSAGE);
        }
    }

    public static Email of(String email) {
        return new Email(email);
    }
}