package com.yogurt.domain.staff.domain;

import com.yogurt.domain.base.entity.BaseEntity;
import com.yogurt.domain.base.model.Date;
import com.yogurt.domain.user.domain.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Staff extends BaseEntity {

    @OneToOne(cascade = {CascadeType.ALL})
    private User user;

    @Builder.Default
    @OneToMany(cascade = {CascadeType.ALL})
    private List<StaffSchedule> schedules = new ArrayList<>();

    @Column(columnDefinition = "TEXT")
    private String selfIntroduce;

    @Column
    private String name;

    @AttributeOverrides({
            @AttributeOverride( name = "date", column = @Column(name = "hired_at", nullable = false, length = 10)),
    })
    private Date hiredAt;

    public String getHiredAt() {
        return hiredAt.getDate();
    }
}
