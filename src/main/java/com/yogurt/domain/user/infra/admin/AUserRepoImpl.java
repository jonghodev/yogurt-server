package com.yogurt.domain.user.infra.admin;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yogurt.domain.lecture.domain.LectureItem;
import com.yogurt.domain.user.dto.admin.response.UsersResponse;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.yogurt.domain.user.domain.QUser.user;

@Repository
public class AUserRepoImpl extends QuerydslRepositorySupport implements AUserRepoCustom {

    private final JPAQueryFactory queryFactory;

    public AUserRepoImpl(JPAQueryFactory queryFactory) {
        super(LectureItem.class);
        this.queryFactory = queryFactory;
    }

    public List<UsersResponse> getAllWithFilter(Pageable pageable) {
        JPAQuery<UsersResponse> query = queryFactory
                .select(Projections.constructor(UsersResponse.class,
                        user.id,
                        user.email,
                        user.authType,
                        user.name,
                        user.phone,
                        user.profileUrl,
                        user.role,
                        user.createdAt
                        ))
                .from(user);

        QueryResults<UsersResponse> result = query.offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetchResults();

        List<UsersResponse> userList = new PageImpl<>(result.getResults(), pageable, result.getTotal())
                .stream()
                .collect(Collectors.toList());

        return userList;
    }
}
