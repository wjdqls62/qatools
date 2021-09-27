package com.js.qa.qatools;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
@Log4j2
public class CommonController {
    @GetMapping("/")
    public String index(){
        return "redirect:/testlink";
    }

    @GetMapping("/testlink")
    public String testlink_index(){
        return "testlink/testlink_index";
    }
}