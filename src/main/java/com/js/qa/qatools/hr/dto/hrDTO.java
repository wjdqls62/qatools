package com.js.qa.qatools.hr.dto;

import com.js.qa.qatools.hr.entity.mysql.mysql_dept_info;
import com.js.qa.qatools.hr.entity.mysql.mysql_user_grade;
import com.js.qa.qatools.hr.entity.sqlserver.sqlserver_dept_info;
import com.js.qa.qatools.hr.entity.sqlserver.sqlserver_user_grade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class hrDTO {
    private String EMAIL;
    private String PASSWORD;
    private Integer NO;
    private String NAME;
    private String GRP;
    private Character GRP_ADMIN;
    private String DOMAIN;
    private Character EARS_USE;
    private Character EARS_EXPIRE_ACTION;
    private String ROUTE_SERVER;
    private String FORWARD_SERVER;
    private String ROUTE_EMAIL;
    private Character RUN_MODE;
    private String FILTER_ORDER;
    private Character APT_USE;
    private Character DETOX_USE;
    private String LANG;
    private mysql_user_grade MYSQL_USR_GRADE;
    private mysql_dept_info MYSQL_DEPT_INFO;
    private sqlserver_user_grade SQLSERVER_USR_GRADE;
    private sqlserver_dept_info SQLSERVER_DEPT_INFO;
    private String USR_MANAGER;
    private String IS_VALID;
    private LocalDateTime regDate, modDate;
}