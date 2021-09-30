package com.js.qa.qatools.hr.controller;

import com.js.qa.qatools.hr.service.mysql.MySQL_HR_Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@RequestMapping("/hr/api/")
@Log4j2
@RestController
public class HrRestController {
    private final MySQL_HR_Service mysqlService;

    // 체크항목 삭제
    @PostMapping("/check_remove")
    public String hr_multiple_remove(@RequestParam(value = "chkList[]") String[] chkList,
                                   RedirectAttributes redirectAttributes){
        for(int i = 0; i < chkList.length; i++){
            mysqlService.hr_remove(Integer.parseInt(chkList[i]));
        }

        redirectAttributes.addFlashAttribute("msg","선택된 항목이 삭제되었습니다.");

        log.info("checkbox remove.");

        return "200";
    }
}