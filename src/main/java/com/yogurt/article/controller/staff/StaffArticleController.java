package com.yogurt.article.controller.staff;

import com.yogurt.article.domain.Article;
import com.yogurt.article.dto.SaveArticleRequest;
import com.yogurt.article.dto.UpdateArticleRequest;
import com.yogurt.article.service.admin.AdminArticleService;
import com.yogurt.article.service.staff.StaffArticleService;
import com.yogurt.base.dto.ApiResponse;
import com.yogurt.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/staff/articles")
public class StaffArticleController {

    private final StaffArticleService service;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAll(@AuthenticationPrincipal User user,
                                              Pageable pageable,
                                              @RequestParam(required = false) Boolean isDeleted) {
        List<Article> articleList = service.getByFilter(pageable, user.getId());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("게시글 리스트입니다.", articleList), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> get(@AuthenticationPrincipal User user, @PathVariable Long id) {
        Article article = service.getByIdAndStudioId(id, user.getStudioId());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("게시글입니다.", article), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> save(@AuthenticationPrincipal User user, @RequestBody @Valid SaveArticleRequest saveArticleRequest) {
        Article article = service.save(saveArticleRequest, user);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("게시글이 저장되었습니다.", article), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> update(@AuthenticationPrincipal User user,
                                              @PathVariable Long id,
                                              @RequestBody @Valid UpdateArticleRequest updateArticleRequest) {
        Article article = service.updateByIdAndStudioId(id, user.getStudioId(), updateArticleRequest);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("게시글입니다.", article), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> delete(@AuthenticationPrincipal User user, @PathVariable Long id) {
        service.deleteByIdAndStudioId(id, user.getStudioId());
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("게시글이 삭제되었습니다."), HttpStatus.OK);
    }
}