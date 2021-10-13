package com.js.qa.qatools.hr.dto;

import com.js.qa.qatools.hr.entity.mysql.dept_info;
import com.js.qa.qatools.hr.entity.mysql.user_grade;
import lombok.Data;

@Data
public class GenerateRequestPageDTO {
    private String count;
    private String lang;
    private String FILTER_ORDER;
    private String check_clear;
    private Integer grade;
    private Integer dept;

    private Character GRP_ADMIN;
    private String mailserver_encrypt;
    private String mailserver_host;
    private String mailserver_port;
    private String GRP;
    private Character APT;
    private Character DETOX;
    private String domainList;
    private String targetDatabase;
    private String passwordType;
    private String customPassword;
    private Character EARS_USE;
    private Character EARS_EXPIRE_ACTION;
    private String ROUTE_SERVER;
    private String ROUTE_EMAIL;
    private Character RUN_MODE;
}