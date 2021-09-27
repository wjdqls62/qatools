package com.js.qa.qatools.testlink.service;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.model.Build;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import com.js.qa.qatools.testlink.dto.QMetricVO;
import org.springframework.stereotype.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@Service
public class TestLinkConnecter extends Thread {
    public static final String devKey = "779d8f6b2877f5ff4ef0aaa5189061f9";
    public static final String testLinkURL = "http://10.53.168.125:82/lib/api/xmlrpc/v1/xmlrpc.php";
    public static final int DEBUG_COUNT = 10;
    public static TestLinkAPI tlApi = null;
    public static URL tlURL = null;

    public TestLinkConnecter(){
        try {
            tlURL = new URL(testLinkURL);
            tlApi = new TestLinkAPI(tlURL, devKey);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    public QMetricVO getQMetric(int testcaseId){

        QMetricVO tempVO = new QMetricVO();

        TestCase testCases = tlApi.getTestCase(
                testcaseId,
                null,
                1);

        tempVO.setTestcaseId(testCases.getId());
        tempVO.setTestcaseName(testCases.getName());
        tempVO.setTestcaseSummary(testCases.getSummary());
        tempVO.setTestcaseStep(testCases.getSteps().toString());
        tempVO.setTestcaseResult(testCases.getExecutionStatus().toString());

        return tempVO;
    }

    public List<QMetricVO> getQMetricList(int projectId, int testPlanId, int buildId, boolean isDebug){

        String customField1 = "품질특성";
        String customField2 = "부특성";
        List<QMetricVO> qMetricVO = new ArrayList<>();

        if(!qMetricVO.isEmpty()){
            qMetricVO.clear();
        }

        // 가장 최근 생성된 계획 및 빌드를 사용기위해 오름차순 정렬된 배열의 0번째(최대값)을 사용
        List<Integer> tempList = new ArrayList<>();
        if(testPlanId == -1 && buildId == -1){
            // 테스트계획ID를 오름차순으로 정렬
            TestPlan[] planList = tlApi.getProjectTestPlans(projectId);
            for(int i = 0; i < planList.length; i++){
                tempList.add(planList[i].getId());
            }
            tempList.sort(Collections.reverseOrder());
            if(isDebug) System.out.println("testPlan reverseOrder Result : " + tempList);
            testPlanId = tempList.get(0).intValue();
            tempList.clear();

            // 테스트빌드ID를 오름차순으로 정렬
            Build[] buildList = tlApi.getBuildsForTestPlan(testPlanId);
            for(int i = 0; i < buildList.length; i++){
                tempList.add(buildList[i].getId());
            }
            tempList.sort(Collections.reverseOrder());
            if(isDebug) System.out.println("buildId reverseOrder Result : " + tempList);
            buildId = tempList.get(0).intValue();
            tempList.clear();
        }

        if(isDebug) System.out.println("ProjectId : " + projectId);
        if(isDebug) System.out.println("PlanId : " + testPlanId);
        if(isDebug) System.out.println("BuildId : " + buildId);

        TestCase[] testCases = tlApi.getTestCasesForTestPlan(
                testPlanId,
                null,
                buildId,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);


        if(isDebug) System.out.println("testCases length : " + testCases.length);


        for(int i = 0; i < testCases.length; i++){
            QMetricVO tempVO = new QMetricVO();
            tempVO.setIndex(i+1);
            tempVO.setProjectId(projectId);
            tempVO.setTestPlanId(testPlanId);
            tempVO.setBuildId(buildId);
            tempVO.setTestcaseId(testCases[i].getId());
            tempVO.setTestcaseSummary(tlApi.getTestCase(testCases[i].getId(), testCases[i].getExternalId(),1).getSummary());
            tempVO.setTestcaseName(tlApi.getTestCase(testCases[i].getId(), testCases[i].getExternalId(),1).getName());
            //tempVO.setTestStep(testCases[i].getSteps().toString());
            tempVO.setTestcaseStep(tlApi.getTestCase(testCases[i].getId(), testCases[i].getExternalId(),1).getSteps().toString());
            tempVO.setCustomField1(tlApi.getTestCaseCustomFieldDesignValue(
                    testCases[i].getId(),
                    testCases[i].getExternalId(),
                    testCases[i].getVersion(),
                    projectId,
                    customField1,
                    null).getValue());
            tempVO.setCustomField2(tlApi.getTestCaseCustomFieldDesignValue(
                    testCases[i].getId(),
                    testCases[i].getExternalId(),
                    testCases[i].getVersion(),
                    projectId,
                    customField2,
                    null).getValue());
            tempVO.setTestcaseResult(testCases[i].getExecutionStatus().toString());

            qMetricVO.add(tempVO);

            // 테스트할때 시간소요가 많아 10건까지만 받아오도록 한다.
            if(isDebug){
                print(tempVO);
                if(i >= DEBUG_COUNT){
                    break;
                }
            }
        }
        return qMetricVO;
    }

    public void print(QMetricVO VO){
        System.out.println("===============");
        System.out.println("index : " + VO.getIndex());
        System.out.println("TestcaseId : " + VO.getTestcaseId());
        System.out.println("Testcase Name : " + VO.getTestcaseName());
        System.out.println("Testcase Summary : " + VO.getTestcaseSummary());
        System.out.println("TestStep : " + VO.getTestcaseStep());
        System.out.println("TestcaseResult : " + VO.getTestcaseResult());
        System.out.println("customField1 : " + VO.getCustomField1());
        System.out.println("customField2 : " + VO.getCustomField2());
        System.out.println("===============");
    }
}