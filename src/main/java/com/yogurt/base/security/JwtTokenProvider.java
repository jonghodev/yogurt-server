package com.yogurt.base.security;

import com.yogurt.api.user.domain.User;
import com.yogurt.base.util.DateUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Value("${yogurt.jwt.secretKey}")
    private String secretKey;

    // 토큰 유효 시간: 일주일
    private long tokenValidTime = 60 * 60 * 24 * 7 * 1000L;

    private final UserDetailService userDetailService;


    // JWT 토큰 생성
    public String createToken(String userPk, String role) {
        Claims claims = Jwts.claims().setSubject(userPk); // JWT payload 에 저장되는 정보단위
        claims.put("role", role); // 정보는 key / value 쌍으로 저장된다.
        Date now = DateUtils.getCurrentDate();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 사용할 암호화 알고리즘과
                // signature 에 들어갈 secret값 세팅
                .compact();
    }

    // JWT 토큰에서 인증 정보 조회
    @Transactional
    public Authentication getAuthentication(String token) {
        User user = userDetailService.getByEmail(getUserPk(token));
        Collection<? extends GrantedAuthority> collection = Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
        return new UsernamePasswordAuthenticationToken(user, "", collection);
    }

    // 토큰에서 회원 정보 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // Request의 Header에서 token 값을 가져옵니다. "Authorization" : "TOKEN값'
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(DateUtils.getCurrentDate());
        } catch (Exception e) {
            return false;
        }
    }
}