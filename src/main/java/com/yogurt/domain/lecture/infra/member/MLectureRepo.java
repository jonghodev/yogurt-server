package com.yogurt.domain.lecture.infra.member;

import com.yogurt.domain.lecture.domain.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MLectureRepo extends JpaRepository<Lecture, Long> {
}
