package com.account.book.test.service.impl;

import com.account.book.test.dao.TestDAO;
import com.account.book.test.dto.TestDTO;
import com.account.book.test.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    private final TestDAO testDAO;


    @Override
    public List<TestDTO> selectTest() {
        return testDAO.selectTest();
    }
}
