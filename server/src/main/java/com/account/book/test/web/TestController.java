package com.account.book.test.web;

import com.account.book.test.vo.TestVO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/hello/world")
    public String helloWorld(){
        return "Hello World";
    }


    @RequestMapping(value = "/hello/world2", method = RequestMethod.GET)
    public TestVO helloWorld2(TestVO vo){
        return vo;
    }

}
