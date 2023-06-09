package com.account.book.group.service;


import com.account.book.group.dto.GroupDTO;
import com.account.book.group.message.dto.GroupMessageDTO;

import java.util.List;

public interface GroupService {
    void createGroup(GroupDTO groupDTO);
    void sendMessage(GroupMessageDTO groupMessageDTO);
    void updateMessageStatus(GroupMessageDTO groupMessageDTO);
    List<GroupMessageDTO> selectMessage(GroupMessageDTO groupMessageDTO, SearchType searchType);
}
