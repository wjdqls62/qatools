package com.js.qa.qatools.hr.controller;

import com.js.qa.qatools.hr.dto.*;
import com.js.qa.qatools.hr.service.mysql.MySQL_HR_Service;
import com.js.qa.qatools.hr.service.sqlserver.SqlServer_HR_Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
@RequestMapping("/hr")
@Log4j2
public class HrController {
    private final String DB_MYSQL = "mysql";
    private final String DB_SQLSERVER = "sqlserver";
    private final String DB_ORACLE = "oracle";
    private final String msg_success = "정상처리되었습니다.";
    private final String msg_fail = "작업이 실패했습니다.";

    private final MySQL_HR_Service mysqlService;
    private final SqlServer_HR_Service sqlServerService;

    @GetMapping("/list")
    public void hr_list(PageRequestDTO pageRequestDTO, Model model){

        // DB타입이 없을경우 MYSQL 기본값 정의
        if(pageRequestDTO.getDbType() == null){
            pageRequestDTO.setDbType(DB_MYSQL);
        }

        if(pageRequestDTO.getSortName() == null){
            pageRequestDTO.setSortName("No");
        }

        if(pageRequestDTO.getSortType() == null){
            pageRequestDTO.setSortType("asc");
        }

        if(pageRequestDTO.getDbType().equals(DB_MYSQL)){
            model.addAttribute("result", mysqlService.getList(pageRequestDTO));
            model.addAttribute("dataLength", mysqlService.hr_getCnt());
        }
        else if(pageRequestDTO.getDbType().equals(DB_SQLSERVER)){
            model.addAttribute("result", sqlServerService.getList(pageRequestDTO));
            model.addAttribute("dataLength", sqlServerService.hr_getCnt());
        }

        log.info("list........................" + pageRequestDTO);
        log.info("result : " + model.getAttribute("result"));
        log.info("dataLength : " + model.getAttribute("dataLength"));
    }

    // 생성화면
    @GetMapping("/add")
    public void hr_add(Model model, String targetDatabase){
        if(targetDatabase.equals(DB_MYSQL)){
            model.addAttribute("gradeList", mysqlService.grade_getList());
            model.addAttribute("deptList", mysqlService.dept_getList());
        }else if(targetDatabase.equals(DB_SQLSERVER)){
            model.addAttribute("gradeList", sqlServerService.grade_getList());
            model.addAttribute("deptList", sqlServerService.dept_getList());
        }
        model.addAttribute("dbType", targetDatabase);
        log.info("hr_add apge");
    }

    // 수정화면
    @GetMapping("/modifyHR")
    public void hr_modify(Model model, Integer emp_no, @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO){
        System.out.println("emp_no : " + emp_no);

        if(pageRequestDTO.getDbType().equals(DB_MYSQL)){
            model.addAttribute("gradeList", mysqlService.grade_getList());
            model.addAttribute("deptList", mysqlService.dept_getList());
            model.addAttribute("result", mysqlService.hr_read(emp_no));
        }else if(pageRequestDTO.getDbType().equals(DB_SQLSERVER)){
            model.addAttribute("gradeList", sqlServerService.grade_getList());
            model.addAttribute("deptList", sqlServerService.dept_getList());
            model.addAttribute("result", sqlServerService.hr_read(emp_no));
        }
        model.addAttribute("dbType", pageRequestDTO.getDbType());
        model.addAttribute("msg", pageRequestDTO.getDbType());
        log.info("hr_modify apge");
    }

    // HR 수정
    @PostMapping("/actionModify")
    public String hr_modify(hrDTO dto, RedirectAttributes redirectAttributes, @ModelAttribute("pageRequestDTO")PageRequestDTO pageRequestDTO){
        if(pageRequestDTO.getDbType().equals(DB_MYSQL)){
            mysqlService.hr_modify(dto);
        }else if(pageRequestDTO.getDbType().equals(DB_SQLSERVER)){
            sqlServerService.hr_modify(dto);
        }

        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("pageSize", pageRequestDTO.getPageSize());
        redirectAttributes.addAttribute("sortName", pageRequestDTO.getSortName());
        redirectAttributes.addAttribute("sortType", pageRequestDTO.getSortType());
        redirectAttributes.addAttribute("dbType", pageRequestDTO.getDbType());
        redirectAttributes.addFlashAttribute("msg",msg_success);
        return "redirect:/hr/list";
    }

    // 자동생성
    @PostMapping("/autoAdd")
    public String hr_auto_add(GenerateRequestPageDTO generateRequestPageDTO, RedirectAttributes redirectAttributes){
        String redirectDb = "";

        System.out.println("@@@" + generateRequestPageDTO.getTargetDatabase() + "@@@");
        if(generateRequestPageDTO.getTargetDatabase().equals(DB_MYSQL)){
            redirectDb = DB_MYSQL;
            mysqlService.hr_auto_register(
                    Integer.parseInt(generateRequestPageDTO.getCount()),
                    generateRequestPageDTO.getCheck_clear(),
                    generateRequestPageDTO.getFILTER_ORDER(),
                    generateRequestPageDTO.getLang(),
                    generateRequestPageDTO.getGrade(),
                    generateRequestPageDTO.getDept(),
                    generateRequestPageDTO.getMailserver_encrypt(),
                    generateRequestPageDTO.getMailserver_host(),
                    generateRequestPageDTO.getMailserver_port(),
                    generateRequestPageDTO.getGRP(),
                    generateRequestPageDTO.getGRP_ADMIN(),
                    generateRequestPageDTO.getAPT(),
                    generateRequestPageDTO.getDETOX(),
                    generateRequestPageDTO.getDomainList(),
                    generateRequestPageDTO.getPasswordType(),
                    generateRequestPageDTO.getEARS_USE(),
                    generateRequestPageDTO.getEARS_EXPIRE_ACTION(),
                    generateRequestPageDTO.getROUTE_SERVER(),
                    generateRequestPageDTO.getROUTE_EMAIL(),
                    generateRequestPageDTO.getRUN_MODE(),
                    generateRequestPageDTO.getCustomPassword(),
                    generateRequestPageDTO.getTargetDatabase());
        }else if(generateRequestPageDTO.getTargetDatabase().equals(DB_SQLSERVER)){
            redirectDb = DB_SQLSERVER;
            sqlServerService.hr_auto_register(
                    Integer.parseInt(generateRequestPageDTO.getCount()),
                    generateRequestPageDTO.getCheck_clear(),
                    generateRequestPageDTO.getFILTER_ORDER(),
                    generateRequestPageDTO.getLang(),
                    generateRequestPageDTO.getGrade(),
                    generateRequestPageDTO.getDept(),
                    generateRequestPageDTO.getMailserver_encrypt(),
                    generateRequestPageDTO.getMailserver_host(),
                    generateRequestPageDTO.getMailserver_port(),
                    generateRequestPageDTO.getGRP(),
                    generateRequestPageDTO.getGRP_ADMIN(),
                    generateRequestPageDTO.getAPT(),
                    generateRequestPageDTO.getDETOX(),
                    generateRequestPageDTO.getDomainList(),
                    generateRequestPageDTO.getPasswordType(),
                    generateRequestPageDTO.getEARS_USE(),
                    generateRequestPageDTO.getEARS_EXPIRE_ACTION(),
                    generateRequestPageDTO.getROUTE_SERVER(),
                    generateRequestPageDTO.getROUTE_EMAIL(),
                    generateRequestPageDTO.getRUN_MODE(),
                    generateRequestPageDTO.getCustomPassword(),
                    generateRequestPageDTO.getTargetDatabase());
        }
        redirectAttributes.addFlashAttribute("msg", msg_success);

        return "redirect:/hr/list?dbType=" + redirectDb;
    }

    // 부서추가 동작
    @PostMapping("actionRegisterDept")
    public String dept_add(deptDTO deptDTO, RedirectAttributes redirectAttributes, String dbType){
        if(dbType.equals(DB_MYSQL)){
            mysqlService.dept_register(deptDTO);
        }else if(dbType.equals(DB_SQLSERVER)){
            sqlServerService.dept_register(deptDTO);
        }

        redirectAttributes.addFlashAttribute("msg", msg_success);
        return "redirect:/hr/modifyDept?dbType=" + dbType;
    }


    // 직급수정 화면
    @GetMapping("/modifyGrade")
    public void hr_modify_grade(Model model, String dbType){
        System.out.println("@@@ " + dbType + " @@@");
        if(dbType.equals(DB_MYSQL)){
            model.addAttribute("result", mysqlService.grade_getList());
        }else if(dbType.equals(DB_SQLSERVER)){
            model.addAttribute("result", sqlServerService.grade_getList());
        }
        model.addAttribute("dbType", dbType);
        log.info(dbType);
    }

    // 부서수정 화면
    @GetMapping("/modifyDept")
    public void hr_modify_dept(Model model, String dbType){
        if(dbType.equals(DB_MYSQL)){
            model.addAttribute("result", mysqlService.dept_getList());
        }else if(dbType.equals(DB_SQLSERVER)){
            model.addAttribute("result", sqlServerService.dept_getList());
        }
        model.addAttribute("dbType", dbType);
        log.info(mysqlService.dept_getList());
    }

    // 모두삭제
    @PostMapping("/allRemove")
    public String hr_removeAll(RedirectAttributes redirectAttributes, String dbType){
        if(dbType.equals(DB_MYSQL)){
            if(mysqlService.hr_getCnt() == 0){
                redirectAttributes.addFlashAttribute("msg","삭제할 항목이 존재하지 않습니다.");
                log.info("not exists remove target.");
            }else{
                mysqlService.hr_removeAll();
                log.info("remove all.");
                redirectAttributes.addFlashAttribute("msg","모두 삭제되었습니다.");
            }
        }else if(dbType.equals(DB_SQLSERVER)){
            if(sqlServerService.hr_getCnt() == 0){
                redirectAttributes.addFlashAttribute("msg","삭제할 항목이 존재하지 않습니다.");
                log.info("not exists remove target.");
            }else{
                sqlServerService.hr_removeAll();
                log.info("remove all.");
                redirectAttributes.addFlashAttribute("msg","모두 삭제되었습니다.");
            }
        }

        return "redirect:/hr/list";
    }

    /**
     * 2021.09.30 @jbson
     * Method를 RestController로 이동하여 주석처리
    // 체크항목 삭제
    @PostMapping("/check_remove")
    public void hr_multiple_remove(@RequestParam(value = "chkList[]") String[] chkList,
                                     RedirectAttributes redirectAttributes){
        for(int i = 0; i < chkList.length; i++){
            mysqlService.hr_remove(Integer.parseInt(chkList[i]));
        }

        redirectAttributes.addFlashAttribute("msg","선택된 항목이 삭제되었습니다.");

        log.info("checkbox remove.");
    }
    **/

    // 단일항목 삭제
    @PostMapping("/remove")
    public String hr_remove(Integer no, RedirectAttributes redirectAttributes){
        mysqlService.hr_remove(no);

        redirectAttributes.addFlashAttribute("msg",msg_success);

        log.info(no + "remove.");
        return "redirect:/hr/list";
    }

    // 부서수정 동작
    @PostMapping("/actionModifyDept")
    public String dept_modify(deptDTO deptDto, RedirectAttributes redirectAttributes, String dbType){
        if(dbType.equals(DB_MYSQL)){
            mysqlService.dept_modify(deptDto);
        }else if(dbType.equals(DB_SQLSERVER)){
            sqlServerService.dept_modify(deptDto);
        }

        redirectAttributes.addFlashAttribute("msg", msg_success);
        return "redirect:/hr/modifyDept?dbType=" + dbType;
    }

    // 부서삭제 동작
    @PostMapping("/actionRemoveDept")
    public String dept_remove(deptDTO deptDto, RedirectAttributes redirectAttributes, String dbType){
        if(dbType.equals(DB_MYSQL)){
            mysqlService.dept_remove(deptDto.getDept_num());
        }else if(dbType.equals(DB_SQLSERVER)){
            sqlServerService.dept_remove(deptDto.getDept_num());
        }

        redirectAttributes.addFlashAttribute("msg", msg_success);
        return "redirect:/hr/modifyDept?dbType=" + dbType;
    }

    // 직급수정 동작
    @PostMapping("/actionModifyGrade")
    public String grade_modify(gradeDTO gradeDTO, RedirectAttributes redirectAttributes, String dbType){
        if(dbType.equals(DB_MYSQL)){
            mysqlService.grade_modify(gradeDTO);
        }else if(dbType.equals(DB_SQLSERVER)){
            sqlServerService.grade_modify(gradeDTO);
        }

        redirectAttributes.addFlashAttribute("msg", msg_success);
        return "redirect:/hr/modifyGrade?dbType=" + dbType;
    }

    // 직급삭제 동작
    @PostMapping("/actionRemoveGrade")
    public String grade_remove(gradeDTO gradeDTO, RedirectAttributes redirectAttributes, String dbType){
        if(dbType.equals(DB_MYSQL)){
            mysqlService.grade_remove(gradeDTO.getGrade_num());
        }else if(dbType.equals(DB_SQLSERVER)){
            sqlServerService.grade_remove(gradeDTO.getGrade_num());
        }

        redirectAttributes.addFlashAttribute("msg", msg_success);
        return "redirect:/hr/modifyGrade?dbType=" + dbType;
    }

    // 직급 추가
    @PostMapping("/actionRegisterGrade")
    public String grade_register(gradeDTO gradeDTO, RedirectAttributes redirectAttributes, String dbType){
        if(dbType.equals(DB_MYSQL)){
            mysqlService.grade_register(gradeDTO);
        }else if(dbType.equals(DB_SQLSERVER)){
            sqlServerService.grade_register(gradeDTO);
        }

        redirectAttributes.addFlashAttribute("msg", msg_success);
        return "redirect:/hr/modifyGrade?dbType=" + dbType;
    }
}