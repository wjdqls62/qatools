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
                          String filterOrder,
                          String lang,
                          Integer grade,
                          Integer dept,
                          String mailserver_encrypt,
                          String mailserver_host,
                          String mailserver_port,
                          String grp,
                          Character grpAdmin,
                          Character apt,
                          Character detox,
                          String domainList,
                          String passwordType,
                          Character ears_use,
                          Character earsExpireAction,
                          String routeServer,
                          String routeEmail,
                          Character runMode,
                          String customPassword);
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
                .no(dto.getNO())
                .name(dto.getNAME())
                .grp(dto.getGRP())
                .grpAdmin(dto.getGRP_ADMIN())
                .domain(dto.getDOMAIN())
                .earsUse(dto.getEARS_USE())
                .earsExpireAction(dto.getEARS_EXPIRE_ACTION())
                .routeServer(dto.getROUTE_SERVER())
                .forwardServer(dto.getFORWARD_SERVER())
                .routeEmail(dto.getROUTE_EMAIL())
                .runMode(dto.getRUN_MODE())
                .filterOrder(dto.getFILTER_ORDER())
                .aptUse(dto.getAPT_USE())
                .detoxUse(dto.getDETOX_USE())
                .lang(dto.getLANG())
                .usrGrade(dto.getUSR_GRADE())
                .usrDept(dto.getDEPT_INFO())
                .usrManager(dto.getUSR_MANAGER())
                .isValid(dto.getIS_VALID())
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
                .NO(entity.getNo())
                .NAME(entity.getName())
                .GRP(entity.getGrp())
                .GRP_ADMIN(entity.getGrpAdmin())
                .DOMAIN(entity.getDomain())
                .EARS_USE(entity.getEarsUse())
                .EARS_EXPIRE_ACTION(entity.getEarsExpireAction())
                .ROUTE_SERVER(entity.getRouteServer())
                .FORWARD_SERVER(entity.getForwardServer())
                .ROUTE_EMAIL(entity.getRouteEmail())
                .RUN_MODE(entity.getRunMode())
                .FILTER_ORDER(entity.getFilterOrder())
                .APT_USE(entity.getAptUse())
                .DETOX_USE(entity.getDetoxUse())
                .LANG(entity.getLang())
                .USR_GRADE(entity.getUsrGrade())
                .DEPT_INFO(entity.getUsrDept())
                .USR_MANAGER(entity.getUsrManager())
                .IS_VALID(entity.getIsValid())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

        return dto;
    }
}