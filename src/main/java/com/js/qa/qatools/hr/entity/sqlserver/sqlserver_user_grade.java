package com.js.qa.qatools.hr.entity.sqlserver;

import lombok.*;
import javax.persistence.*;

@Entity
@Table (schema = "insa")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class sqlserver_user_grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_num")
    private int grade_num;

    @Column(length = 50)
    private String grade_name;

    public void changeGradeNum(int grade_num){
        this.grade_num = grade_num;
    }

    public void changeGradeName(String grade_name){
        this.grade_name = grade_name;
    }
}