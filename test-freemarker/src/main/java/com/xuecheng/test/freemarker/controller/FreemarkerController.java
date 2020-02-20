package com.xuecheng.test.freemarker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author code
 */
@Controller
@RequestMapping("/freemarker")
public class FreemarkerController {

    @RequestMapping("/test1")
    public String test1(Map<String,Object> map){
        map.put("name", "code00000001");
        return "test1";
    }
}
