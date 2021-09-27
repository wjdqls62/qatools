package com.js.qa.qatools.hr.repository.mysql;

import com.js.qa.qatools.hr.dto.PageRequestDTO;
import com.js.qa.qatools.hr.dto.PageResultDTO;
import com.js.qa.qatools.hr.dto.hrDTO;
import com.js.qa.qatools.hr.entity.mysql.dept_info;
import com.js.qa.qatools.hr.entity.mysql.qa_test_hr;
import com.js.qa.qatools.hr.entity.mysql.user_grade;
import com.js.qa.qatools.hr.service.mysql.MySQL_HR_Service;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MySQL_Service_Test {

    @Autowired
    private MySQL_HR_Service mySQL_hr_service;

    @Test
    public void test_HR_Register(){
        user_grade grade = user_grade.builder()
                .grade_name("아아아아")
                .build();
        dept_info dept = dept_info.builder()
                .dept_name("크아아아아")
                .dept_parent("지란지교")
                .build();

        hrDTO dto = hrDTO.builder()
                .EMAIL("wjdqls622@naver.com")
                .PASSWORD("vlfvlfdl")
                .NAME("TEST-JBSON")
                .LANG("Ko")
                .USR_GRADE(mySQL_hr_service.dtoToEntity(mySQL_hr_service.grade_read(1)))
                .DEPT_INFO(mySQL_hr_service.dtoToEntity(mySQL_hr_service.dept_read(1)))
                .build();

        System.out.println(mySQL_hr_service.hr_register(dto));
    }

    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(13).pageSize(10).build();
        PageResultDTO<hrDTO, qa_test_hr> resultDTO = mySQL_hr_service.getList(pageRequestDTO);

        System.out.println("PREV : " + resultDTO.isPrev());
        System.out.println("NEXT : " + resultDTO.isNext());
        System.out.println("TOTAL : " + resultDTO.getTotalPage());

        System.out.println("========================================");
        for(hrDTO hrDTO : resultDTO.getDtoList()){
            System.out.println(hrDTO);
        }

        System.out.println("========================================");
        resultDTO.getPageList().forEach(i -> System.out.println(i));
    }
}