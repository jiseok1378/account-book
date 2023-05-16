
package com.account.book.simulation;

import com.account.book.pair.dto.PairDTO;
import com.account.book.user.dto.UserDTO;
import com.account.book.user.service.UserService;
import com.account.book.user.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.DisplayName;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;


import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") // 자바 MockMvc 경고 무시
@DisplayName("시스템 통합 시뮬레이션 테스트")
public class SystemSimulation {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
                .alwaysDo(print())
                .build();
    }

    @Test
    void test() throws Exception {
        final String userId1 = "user1";
        UserDTO user1 = new UserDTO();
        user1.setUserId(userId1);
        user1.setUserNm(userId1);
        user1.setUserPw(userId1);
        mvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user1))
        ).andExpect(status().isOk());


        UserDTO selectedUser1 = mapper.readValue(mvc.perform(get("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .param("userId", userId1))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(), UserDTO.class);


        final String userId2 = "user2";
        UserDTO user2 = new UserDTO();
        user2.setUserId(userId2);
        user2.setUserNm(userId2);
        user2.setUserPw(userId2);
        mvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user2))
        ).andExpect(status().isOk());

        UserDTO selectedUser2 = mapper.readValue(mvc.perform(get("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .param("userId", userId2))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(), UserDTO.class);

        PairDTO pairDTO = new PairDTO();
        pairDTO.setUserSnTo(selectedUser1.getUserSn());
        pairDTO.setUserSnFrom(selectedUser2.getUserSn());
        pairDTO.setPairMsg("나의 페어가 되어주세요!");

        mvc.perform(post("/api/pair")
                .content(mapper.writeValueAsString(pairDTO))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        PairDTO selectedPair = mapper.readValue(mvc.perform(get("/api/pair")
                .param("userSn", Integer.toString(selectedUser1.getUserSn()))
                .contentType(MediaType.APPLICATION_JSON)
//                .characterEncoding(StandardCharsets.UTF_8)
        ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), PairDTO.class);

        System.out.println(selectedPair.getPairMsg() + ":" + selectedPair.getAccept());

        mvc.perform(
                delete("/api/user")
                        .param("userSn", Integer.toString(selectedUser1.getUserSn()))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        mvc.perform(
                delete("/api/user")
                        .param("userSn", Integer.toString(selectedUser2.getUserSn()))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());



    }
}
        