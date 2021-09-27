package com.js.qa.qatools.hr.entity.mysql;

import com.js.qa.qatools.hr.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class qa_test_hr extends BaseEntity {

    @Column(length = 255)
    private String EMAIL;

    @Column(length = 64)
    private String PASSWORD;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer NO;

    @Column(length = 30)
    private String NAME;

    @Column(length = 255)
    private String GRP;

    @Column(length = 1)
    private Character GRP_ADMIN;

    @Column(length = 255)
    private String DOMAIN;

    @Column(length = 1)
    private String EARS_USE;

    @Column(length = 1)
    private Character EARS_EXPIRE_ACTION;

    @Column(length = 100)
    private String ROUTE_SERVER;

    @Column(length = 100)
    private String FORWARD_SERVER;

    @Column(length = 255)
    private String ROUTE_EMAIL;

    @Column(length = 1)
    private Character RUN_MODE;

    @Column(length = 8)
    private String FILTER_ORDER;

    @Column(length = 1)
    private Character APT_USE;

    @Column(length = 1)
    private Character DETOX_USE;

    @Column(length = 2)
    private String LANG;

    @ManyToOne
    @JoinColumn(name = "USR_GRADE")
    private user_grade USR_GRADE;

    @ManyToOne
    @JoinColumn(name = "USR_DEPT")
    private dept_info DEPT_INFO;

    @Column(length = 30)
    private String USR_MANAGER;

    @Column(length = 1)
    private String IS_VALID;
}