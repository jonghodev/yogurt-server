package com.yogurt.domain.lecture.api.admin;

import com.yogurt.domain.auth.domain.AuthContext;
import com.yogurt.domain.lecture.domain.Lecture;
import com.yogurt.domain.lecture.domain.LectureItem;
import com.yogurt.domain.lecture.dto.admin.LecturesResponse;
import com.yogurt.domain.lecture.dto.admin.SaveLecturesRequest;
import com.yogurt.domain.lecture.service.admin.AdminLectureService;
import com.yogurt.global.common.response.ApiResponse;
import com.yogurt.global.common.response.Meta;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/lectures")
public class AdminLectureApi {

    private final AdminLectureService service;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAllWithFilter(@AuthenticationPrincipal AuthContext authContext,
                                                        Pageable pageable,
                                                        @RequestParam(value = "start_at") String startAt,
                                                        @RequestParam(value = "end_at") String endAt,
                                                        @RequestParam(value = "week_day", required = false) String weekDay,
                                                        @RequestParam(value = "staff_id", required = false) Long staffId,
                                                        @RequestParam(value = "class_type", required = false) String classType) {
        List<LecturesResponse> lectureItemList = service.getAllWithFilter(authContext.getStudioId(), pageable, startAt, endAt, weekDay, staffId, classType);

        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("수업 리스트입니다.", lectureItemList, Meta.of(pageable, lectureItemList.size())), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> save(@RequestBody @Validated SaveLecturesRequest saveLecturesRequest) {
        Lecture lecture = service.create(saveLecturesRequest);
        return new ResponseEntity<>(ApiResponse.createSuccessApiResponse("수업이 저장되었습니다.", lecture), HttpStatus.OK);
    }
}
