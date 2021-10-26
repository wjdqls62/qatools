package com.js.qa.qatools.hr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class gradeDTO {
    private int grade_num;
    private String grade_name;
    private String dbType;
}
