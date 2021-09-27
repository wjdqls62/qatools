package com.js.qa.qatools.hr.service.mysql;

import com.js.qa.qatools.hr.dto.*;
import com.js.qa.qatools.hr.entity.mysql.dept_info;
import com.js.qa.qatools.hr.entity.mysql.qa_test_hr;
import com.js.qa.qatools.hr.entity.mysql.user_grade;

import java.util.List;

public interface MySQL_HR_Service {
    PageResultDTO<hrDTO, qa_test_hr> getList(PageRequestDTO requestDTO);

    /** QA_TEST_HR **/
    Integer hr_register(hrDTO dto);
    void hr_auto_register(int count,
                          String isClear,
                          String filterMode,
                          String lang,
                          Integer grade,
                          Integer dept,
                          String mailserver_encrypt,
                          String mailserver_host,
                          String mailserver_port,
                          String grp,
                          Character grp_admin,
                          Character apt,
                          Character detox,
                          String domainList);
    hrDTO hr_read(Integer emp_no);
    void hr_remove(Integer emp_no);
    void hr_removeAll();
    void hr_modify(hrDTO dto);
    long hr_getCnt();

    /** dept **/
    List<dept_info>  dept_getList();
    Integer dept_register(hrDTO dto);
    deptDTO dept_read(Integer dept_num);
    void dept_remove(Integer dept_num);
    void dept_modify(deptDTO dto);
    void dept_register(deptDTO dto);

    /** grade **/
    List<user_grade> grade_getList();
    Integer grade_register(hrDTO dto);
    gradeDTO grade_read(Integer grade_num);
    void grade_remove(Integer grade_num);
    void grade_modify(gradeDTO dto);
    void grade_register(gradeDTO dto);

    default user_grade dtoToEntity(gradeDTO dto){
        user_grade entity = user_grade.builder()
                .grade_name(dto.getGrade_name())
                .grade_num(dto.getGrade_num())
                .build();
        return entity;
    }

    default dept_info dtoToEntity(deptDTO dto){
        dept_info entity = dept_info.builder()
                .dept_num(dto.getDept_num())
                .dept_name(dto.getDept_name())
                .dept_parent(dto.getDept_parent())
                .build();
        return entity;
    }

    default qa_test_hr dtoToEntity(hrDTO dto){
        qa_test_hr entity = qa_test_hr.builder()
                .EMAIL(dto.getEMAIL())
                .PASSWORD(dto.getPASSWORD())
                .NO(dto.getNO())
                .NAME(dto.getNAME())
                .GRP(dto.getGRP())
                .GRP_ADMIN(dto.getGRP_ADMIN())
                .DOMAIN(dto.getDOMAIN())
                .EARS_USE(dto.getEARS_USE())
                .EARS_EXPIRE_ACTION(dto.getEARS_EXPIRE_ACTION())
                .ROUTE_SERVER(dto.getROUTE_SERVER())
                .FORWARD_SERVER(dto.getFORWARD_SERVER())
                .ROUTE_EMAIL(dto.getROUTE_EMAIL())
                .RUN_MODE(dto.getRUN_MODE())
                .FILTER_ORDER(dto.getFILTER_ORDER())
                .APT_USE(dto.getAPT_USE())
                .DETOX_USE(dto.getDETOX_USE())
                .LANG(dto.getLANG())
                .USR_GRADE(dto.getUSR_GRADE())
                .DEPT_INFO(dto.getDEPT_INFO())
                .USR_MANAGER(dto.getUSR_MANAGER())
                .IS_VALID(dto.getIS_VALID())
                .build();

        return entity;
    }

    default deptDTO entityToDTO(dept_info entity){
        deptDTO dto = deptDTO.builder()
                .dept_num(entity.getDept_num())
                .dept_name(entity.getDept_name())
                .dept_parent(entity.getDept_parent())
                .build();

        return dto;
    }

    default gradeDTO entityToDTO(user_grade entity){
        gradeDTO dto = gradeDTO.builder()
                .grade_num(entity.getGrade_num())
                .grade_name(entity.getGrade_name())
                .build();

        return dto;
    }

    default hrDTO entityToDTO(qa_test_hr entity){
        hrDTO dto = hrDTO.builder()
                .EMAIL(entity.getEMAIL())
                .PASSWORD(entity.getPASSWORD())
                .NO(entity.getNO())
                .NAME(entity.getNAME())
                .GRP(entity.getGRP())
                .GRP_ADMIN(entity.getGRP_ADMIN())
                .DOMAIN(entity.getDOMAIN())
                .EARS_USE(entity.getEARS_USE())
                .EARS_EXPIRE_ACTION(entity.getEARS_EXPIRE_ACTION())
                .ROUTE_SERVER(entity.getROUTE_SERVER())
                .FORWARD_SERVER(entity.getFORWARD_SERVER())
                .ROUTE_EMAIL(entity.getROUTE_EMAIL())
                .RUN_MODE(entity.getRUN_MODE())
                .FILTER_ORDER(entity.getFILTER_ORDER())
                .APT_USE(entity.getAPT_USE())
                .DETOX_USE(entity.getDETOX_USE())
                .LANG(entity.getLANG())
                .USR_GRADE(entity.getUSR_GRADE())
                .DEPT_INFO(entity.getDEPT_INFO())
                .USR_MANAGER(entity.getUSR_MANAGER())
                .IS_VALID(entity.getIS_VALID())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

        return dto;
    }
}