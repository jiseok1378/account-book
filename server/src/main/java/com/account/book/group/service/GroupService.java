package com.account.book.group.service;


import com.account.book.group.dto.GroupDTO;
import com.account.book.group.message.dto.GroupMessageDTO;

import java.util.List;

public interface GroupService {
    /**
     * 그룹 생성
     * @param groupDTO
     */
    void createGroup(GroupDTO groupDTO);

    /**
     * 그룹 초청 메세지 전송
     * @param groupMessageDTO
     */
    void sendMessage(GroupMessageDTO groupMessageDTO);

    /**
     * 메세지 상태 변경 (수락, 거부 등)
     * @param groupMessageDTO
     */
    void updateMessageStatus(GroupMessageDTO groupMessageDTO);

    /**
     * 메세지 조회
     * @param groupMessageDTO
     * @param searchType
     * @return
     */
    List<GroupMessageDTO> selectMessage(GroupMessageDTO groupMessageDTO, SearchType searchType);
}
