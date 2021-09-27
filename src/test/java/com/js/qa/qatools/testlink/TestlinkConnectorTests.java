package com.js.qa.qatools.testlink;
import com.js.qa.qatools.testlink.dto.QMetricVO;
import com.js.qa.qatools.testlink.service.TestLinkConnecter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class TestlinkConnectorTests {

    @Autowired
    private TestLinkConnecter connecter;

    @Test
    public void Test(){
        List<QMetricVO> list = connecter.getQMetricList(85699, -1, -1,true);

        System.out.println(list);
    }

    @Test
    public void Test2(){
        QMetricVO testCase = connecter.getQMetric(86062);

        System.out.println(testCase);
        System.out.println("DDD");
    }
}