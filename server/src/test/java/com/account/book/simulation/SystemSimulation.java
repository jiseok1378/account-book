
package com.account.book.simulation;

import com.account.book.acbook.dto.AccountBookDTO;
import com.account.book.category.dto.CategoryDTO;
import com.account.book.category.dto.MenuCategoryDTO;
import com.account.book.cmmn.util.response.ListResult;
import com.account.book.cmmn.util.response.SingleResult;
import com.account.book.group.dto.GroupDTO;
import com.account.book.group.message.dto.GroupMessageDTO;
import com.account.book.group.message.dto.GroupMessageStatus;
import com.account.book.menu.dto.MenuDTO;
//import com.account.book.pair.dto.AcceptStatus;
//import com.account.book.pair.dto.PairDTO;
import com.account.book.user.dto.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
@TestMethodOrder(MethodOrderer.MethodName.class) // 이 클래스는 알파벳 순서대로 시스템이 동작하므로 a~z 이름의 함수명을 쓸 것.
public class SystemSimulation {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private ObjectMapper mapper;

    private static Integer user1Sn;
    private static Integer user2Sn;
    private static Integer groupSn;
    private static Integer categorySn;
    private static Integer lowerMenuSn;
    private static Integer upperMenuSn;
    private static final String updatedCategoryNm = "네가 바로 코난이냐?";

    @BeforeEach
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
                .alwaysDo(print())
                .build();

    }

    @Test
    @DisplayName("유저 생성 및 조회")
    void a() throws Exception {
        final String userId1 = "user1";

        UserDTO user1 = new UserDTO();
        user1.setUserId(userId1);
        user1.setUserNm(userId1);
        user1.setUserPw(userId1);
        mvc.perform(post("/api/authenticate/sign/up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user1))
        ).andExpect(status().isOk());

        final String userId2 = "user2";
        UserDTO user2 = new UserDTO();
        user2.setUserId(userId2);
        user2.setUserNm(userId2);
        user2.setUserPw(userId2);
        mvc.perform(post("/api/authenticate/sign/up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user2))
        ).andExpect(status().isOk());

        SingleResult<UserDTO> selectedUser1 = mapper.readValue(mvc.perform(post("/api/authenticate/sign/in")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user1)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(), new TypeReference<>(){});

        SingleResult<UserDTO> selectedUser2 = mapper.readValue(mvc.perform(post("/api/authenticate/sign/in")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user2)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(), new TypeReference<>(){});

        user1Sn = selectedUser1.getData().getUserSn();
        user2Sn = selectedUser2.getData().getUserSn();

    }

    @DisplayName("그룹 생성, 수락, 조회")
    @Test
    void b() throws Exception {
        GroupDTO group = new GroupDTO();
        group.setOwnerUserSn(user1Sn);
        group.setGroupNm("그룹");
        String createRes = mvc.perform(post("/api/group")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(group))
        ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        groupSn = Integer.parseInt(createRes);

        GroupMessageDTO messageDTO = new GroupMessageDTO();

        messageDTO.setFromUserSn(user1Sn);
        messageDTO.setMessageCn("나의 멤버가 될래요?");
        messageDTO.setToUserSn(user2Sn);
        messageDTO.setGroupSn(groupSn);

        String sendRes = mvc.perform(post("/api/group/message").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(messageDTO))).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        Integer messageSn = Integer.parseInt(sendRes);

        messageDTO.setMessageSn(messageSn);
        messageDTO.setMessageStatus(GroupMessageStatus.ACCEPT.getCode());

        mvc.perform(put("/api/group/message/status").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(messageDTO))).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        System.out.println(createRes);

        String fromRes = mvc.perform(get("/api/group/message")
                .param("fromUserSn", Integer.toString(user1Sn))
                .param("type", "FROM")
        ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        System.out.println(fromRes);

        String toRes = mvc.perform(get("/api/group/message")
                .param("toUserSn", Integer.toString(user2Sn))
                .param("type", "TO")
        ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        System.out.println(toRes);


    }

    @DisplayName("카테고리 생성, 업데이트, 조회")
    @Test
    void c() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryNm("내 이름은 카테고리. 탐정이죠.");
        categoryDTO.setGroupSn( groupSn );

        SingleResult<Integer> insertResult = mapper.readValue(mvc.perform(
                post("/api/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(categoryDTO))
        ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), new TypeReference<SingleResult<Integer>>() {});

        categorySn = insertResult.getData();

        assertNotNull( categorySn );

        categoryDTO.setCategorySn( categorySn );
        categoryDTO.setCategoryNm( updatedCategoryNm );

        MultiValueMap<String, String> valueMap = new LinkedMultiValueMap<>();
        valueMap.put("categoryNm", Collections.singletonList(categoryDTO.getCategoryNm()));
        valueMap.put("categorySn", Collections.singletonList(Integer.toString(categoryDTO.getCategorySn())));
        valueMap.put("otherColumn", Collections.singletonList(categoryDTO.getOtherColumn()));

        mvc.perform(
                put("/api/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .params(valueMap)
        ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        ListResult<CategoryDTO> updated = mapper.readValue(mvc.perform(
                get("/api/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("groupSn", Integer.toString(groupSn))
        ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), new TypeReference<>(){});

        assertEquals(updatedCategoryNm, updated.getData().get(0).getCategoryNm());
    }

    @DisplayName("메뉴 생성, 업데이트, 조회")
    @Test
    void d() throws Exception {
        MenuDTO upperMenu = new MenuDTO();
        upperMenu.setMenuNm("상위 메뉴");
        upperMenu.setGroupSn( groupSn );

         SingleResult<Integer> upperinsertedRes = mapper.readValue(
                 mvc.perform(post("/api/menu")
                         .content(mapper.writeValueAsString(upperMenu))
                         .contentType(MediaType.APPLICATION_JSON))
                         .andExpect(status().isOk()).andReturn().getResponse().getContentAsString()
                 , new TypeReference<>() {});

        upperMenuSn = upperinsertedRes.getData();
        MenuDTO lowerMenu = new MenuDTO();
        lowerMenu.setMenuNm("하위 메뉴");
        lowerMenu.setUpperMenuSn(upperMenuSn);
        lowerMenu.setGroupSn(groupSn);

         SingleResult<Integer> lowerInsertedRes = mapper.readValue(
                mvc.perform(post("/api/menu")
                        .content(mapper.writeValueAsString(lowerMenu))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk()).andReturn().getResponse().getContentAsString()
                , new TypeReference<>() {});
        lowerMenuSn = lowerInsertedRes.getData();
        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        String updateMenuNm = "상위 메뉴 이름 업데이트";
        upperMenu.setMenuNm(updateMenuNm);
        map.put("menuNm", Collections.singletonList(upperMenu.getMenuNm()));
        map.put("menuSn", Collections.singletonList(Integer.toString(upperMenuSn)));

        mvc.perform(put("/api/menu")
                .params(map)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();


        SingleResult<MenuDTO> updatedMenu = mapper.readValue(
                mvc.perform(get("/api/menu")
                        .param("menuSn", Integer.toString(upperMenuSn))
                        .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString(), new TypeReference<SingleResult<MenuDTO>>() {});

        assertEquals(updatedMenu.getData().getMenuNm(), updateMenuNm);
    }

    @DisplayName("카테고리 메뉴 등록 및 등록된 카테고리 조회")
    @Test
    void e() throws Exception {
        MenuCategoryDTO menuCategoryDTO = new MenuCategoryDTO();
        menuCategoryDTO.setCategorySn( categorySn );
        menuCategoryDTO.setMenuSn( upperMenuSn );
        menuCategoryDTO.setGroupSn( groupSn );
        mvc.perform(
                post("/api/category/menu")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(menuCategoryDTO))
        ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        ListResult<CategoryDTO> categoryDTOS = mapper.readValue(mvc.perform(
                get("/api/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("groupSn", Integer.toString(groupSn))
                        .param("menuSn", Integer.toString(upperMenuSn))
        ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), new TypeReference<>(){});

        assertEquals(updatedCategoryNm, categoryDTOS.getData().get(0).getCategoryNm());
        assertEquals(1, categoryDTOS.getData().size());
    }

    @DisplayName("가계부 등록 및 업데이트")
    @Test
    void f() throws Exception {
        AccountBookDTO accountBookDTO = new AccountBookDTO();
        accountBookDTO.setCategorySn( categorySn );
        accountBookDTO.setPlace("장소");
        accountBookDTO.setPrice(20);
        mvc.perform(
                post("/api/account/book")
                        .content(mapper.writeValueAsString(accountBookDTO))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        mvc.perform(
                put("/api/account/book")
                        .content(mapper.writeValueAsString(accountBookDTO))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @DisplayName("카테고리 재조회")
    @Test
    void g() throws Exception {
        MenuCategoryDTO menuCategoryDTO = new MenuCategoryDTO();
        menuCategoryDTO.setCategorySn( categorySn );
        menuCategoryDTO.setMenuSn( upperMenuSn );
        menuCategoryDTO.setGroupSn( groupSn );
        mvc.perform(
                post("/api/category/menu")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(menuCategoryDTO))
        ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        ListResult<CategoryDTO> categoryDTOS = mapper.readValue(mvc.perform(
                get("/api/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("groupSn", Integer.toString(groupSn))
                        .param("menuSn", Integer.toString(upperMenuSn))
        ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), new TypeReference<>(){});

        assertEquals(1, categoryDTOS.getData().get(0).getAccountBooks().size());

    }

    @DisplayName("메뉴 삭제")
    @Test
    void x() throws Exception {
        mvc.perform(
                delete("/api/menu")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("menuSn", Integer.toString(lowerMenuSn))
        ).andExpect(status().isOk());
    }


    @DisplayName("카테고리 삭제")
    @Test
    void y() throws Exception {
       mvc.perform(
                delete("/api/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("categorySn", Integer.toString(categorySn))
        ).andExpect(status().isOk());
    }

    @DisplayName("유저 삭제")
    @Test
    void z() throws Exception {
        mvc.perform(
                delete("/api/user")
                        .param("userSn", Integer.toString(user1Sn))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        mvc.perform(
                delete("/api/user")
                        .param("userSn", Integer.toString(user2Sn))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }
}
        