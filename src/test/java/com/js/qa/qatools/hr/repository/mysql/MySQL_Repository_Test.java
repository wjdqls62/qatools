package com.js.qa.qatools.hr.repository.mysql;

import com.js.qa.qatools.hr.entity.mysql.dept_info;
import com.js.qa.qatools.hr.entity.mysql.qa_test_hr;
import com.js.qa.qatools.hr.entity.mysql.user_grade;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MySQL_Repository_Test {
    @Autowired
    UserGrade user_grade_repository;

    @Autowired
    DeptInfo dept_Info_repository;

    @Autowired
    Qa_Test_Hr hr_repository;

    @Test
    public void insertGrade(){
        user_grade grade = user_grade.builder()
                .grade_name("Test")
                .build();

        user_grade_repository.save(grade);
    }

    @Test
    public void insertDept(){
        dept_info dept = new dept_info();
        dept.setDept_name("부서이름");
        dept.setDept_parent("지란시큐리티11");
        dept_Info_repository.save(dept);
    }

    @Test
    public void insertHR(){
        qa_test_hr hr = qa_test_hr.builder()
                .EMAIL("wjdqls621@naver.com")
                .PASSWORD("vlfvlfdl")
                .NAME("jbson90")
                .FILTER_ORDER("OUaA")
                .LANG("Ko")
                .DEPT_INFO(dept_Info_repository.findById(1).get())
                .USR_GRADE(user_grade_repository.findById(1).get())
                .build();

        hr_repository.save(hr);
    }

}