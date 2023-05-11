package com.account.book.test.web;

import com.account.book.test.dao.TestDAO;
import com.account.book.test.dto.TestDTO;
import com.account.book.test.service.TestService;
import com.account.book.test.vo.TestVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/hello/world")
    public String helloWorld(){
        return "Hello World";
    }


    @RequestMapping(value = "/hello/world2", method = RequestMethod.GET)
    public TestVO helloWorld2(TestVO vo){
        return vo;
    }

    @RequestMapping(value = "/hello/test", method = RequestMethod.GET)
    public List<TestDTO> testDTO(){
        return testService.selectTest();
    }
}
