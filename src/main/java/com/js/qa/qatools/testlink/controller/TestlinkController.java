package com.js.qa.qatools.testlink.controller;

import com.js.qa.qatools.testlink.dto.QMetricVO;
import com.js.qa.qatools.testlink.service.TestLinkConnecter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/testlink")
@Log4j2
public class TestlinkController {

    @Autowired
    private final TestLinkConnecter connecter;

    @GetMapping("/api/metric")
    @ResponseBody
    public List<QMetricVO> getTestCases(@RequestParam String projectId,
                                        @RequestParam String testPlanId,
                                        @RequestParam String buildId,
                                        @RequestParam String isDebug,
                                        Model model,
                                        HttpServletResponse response){
        return connecter.getQMetricList(
                Integer.parseInt(projectId),
                Integer.parseInt(testPlanId),
                Integer.parseInt(buildId),
                BooleanUtils.toBooleanObject(isDebug));
    }

    @GetMapping("/api/metric/testcase")
    @ResponseBody
    public QMetricVO getTestCase(@RequestParam String id){
        return connecter.getQMetric(Integer.parseInt(id));
    }

    @GetMapping("/testcase/detail")
    public String testlink_popup(@RequestParam String id, Model model){
        QMetricVO temp = getTestCase(id);

        model.addAttribute("testcaseId", temp.getTestcaseId());
        model.addAttribute("testcaseName", temp.getTestcaseName());
        model.addAttribute("testcaseSummary", temp.getTestcaseSummary());
        model.addAttribute("testcaseStep", temp.getTestcaseStep());
        model.addAttribute("testcaseResult", temp.getTestcaseResult());
        model.addAttribute("customField1", temp.getCustomField1());
        model.addAttribute("customField2", temp.getCustomField2());
        model.addAttribute("buildId", temp.getBuildId());
        model.addAttribute("testPlanId", temp.getTestPlanId());
        model.addAttribute("projectId", temp.getProjectId());

        return "testlink/popup_detail";
    }
}