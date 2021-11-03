package com.js.qa.qatools.hr.entity.sqlserver;

import lombok.*;
import javax.persistence.*;

@Entity
@Data
@Table (schema = "insa", name = "qa_test_hr")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class sqlserver_qa_test_hr {

    @Column(length = 255)
    private String EMAIL;

    @Column(length = 64)
    private String PASSWORD;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NO")
    private Integer no;

    @Column(length = 30, name = "NAME")
    private String name;

    @Column(length = 255, name = "GRP")
    private String grp;

    @Column(length = 1, name = "GRP_ADMIN")
    private Character grpAdmin;

    @Column(length = 255, name = "DOMAIN")
    private String domain;

    @Column(length = 1, name = "EARS_USE")
    private Character earsUse;

    @Column(length = 1, name = "EARS_EXPIRE_ACTION")
    private Character earsExpireAction;

    @Column(length = 100, name = "ROUTE_SERVER")
    private String routeServer;

    @Column(length = 100, name = "FORWARD_SERVER")
    private String forwardServer;

    @Column(length = 255, name = "ROUTE_EMAIL")
    private String routeEmail;

    @Column(length = 1, name = "RUN_MODE")
    private Character runMode;

    @Column(length = 8, name = "FILTER_ORDER")
    private String filterOrder;

    @Column(length = 1, name = "APT_USE")
    private Character aptUse;

    @Column(length = 1, name = "DETOX_USE")
    private Character detoxUse;

    @Column(length = 2, name = "LANG")
    private String lang;

    @OneToOne
    @JoinColumn(name = "USR_GRADE")
    private sqlserver_user_grade usrGrade;

    @OneToOne
    @JoinColumn(name = "USR_DEPT")
    private sqlserver_dept_info usrDept;

    @Column(length = 30, name = "USR_MANAGER")
    private String usrManager;

    @Column(length = 1, name = "IS_VALID")
    private String isValid;
}