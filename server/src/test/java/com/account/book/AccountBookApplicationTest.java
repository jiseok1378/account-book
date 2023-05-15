package com.account.book;

import com.account.book.test.dao.TestDAO;
import com.account.book.test.dto.TestDTO;
import com.account.book.test.service.impl.TestServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") // 자바 MockMvc 경고 무시
public class AccountBookApplicationTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    private TestDAO testDAO;

    @Spy
    @InjectMocks
    private TestServiceImpl TestServiceImpl;

    @Test
    void test() throws Exception {
          ;

        String requestBody = mvc.perform( get("/api/hello/world")
                    .characterEncoding("UTF-8")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println(requestBody);

        ArrayList<TestDTO> mockData = new ArrayList<>();

        mockData.add(TestDTO.builder().id(1).build());
        mockData.add(TestDTO.builder().id(2).build());
        mockData.add(TestDTO.builder().id(3).build());
        mockData.add(TestDTO.builder().id(4).build());

        Mockito.when(TestServiceImpl.selectTest()).thenReturn( mockData );

        List<TestDTO> dtos = TestServiceImpl.selectTest();

        Mockito.verify(TestServiceImpl).selectTest();
    }
}
