package com.js.qa.qatools.hr.entity.mysql;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class dept_info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dept_num;

    @Column(length = 50)
    private String dept_name;

    @Column(length = 50)
    private String dept_parent;

    public void changeDeptName(String dept_name){
        this.dept_name = dept_name;
    }

    public void changeParent(String dept_parent){
        this.dept_parent = dept_parent;
    }
}