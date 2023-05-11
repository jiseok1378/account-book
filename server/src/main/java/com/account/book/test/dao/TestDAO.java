package com.account.book.test.dao;

import com.account.book.test.dto.TestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface TestDAO {
    List<TestDTO> selectTest();
}
