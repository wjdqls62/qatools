package com.js.qa.qatools.hr.dto;

import com.js.qa.qatools.hr.entity.mysql.dept_info;
import com.js.qa.qatools.hr.entity.mysql.user_grade;
import lombok.Data;

@Data
public class GenerateRequestPageDTO {
    private String count;
    private String lang;
    private String filtermode;
    private String check_clear;
    private Integer grade;
    private Integer dept;

    private Character grp_admin;
    private String mailserver_encrypt;
    private String mailserver_host;
    private String mailserver_port;
    private String grp;
    private Character apt;
    private Character detox;
    private String domainList;
    private String targetDatabase;
}