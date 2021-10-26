package com.js.qa.qatools.hr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class deptDTO {
    private int dept_num;
    private String dept_name;
    private String dept_parent;
    private String dbType;
}
