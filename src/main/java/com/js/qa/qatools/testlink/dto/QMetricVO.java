package com.js.qa.qatools.testlink.dto;

import lombok.Data;

import java.util.Date;

@Data
public class QMetricVO {
    private int index;
    private int projectId;
    private int testPlanId;
    private int buildId;
    private int testcaseId;
    private String testcaseName;
    private String testcaseSummary;
    private String testcaseStep;
    private String testcaseResult;
    private String customField1;
    private String customField2;
}
