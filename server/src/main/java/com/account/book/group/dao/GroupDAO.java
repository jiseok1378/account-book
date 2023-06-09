package com.account.book.group.dao;

import com.account.book.group.dto.GroupDTO;
import com.account.book.group.member.dto.GroupMemberDTO;
import com.account.book.group.message.dto.GroupMessageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface GroupDAO {
    void addGroup(GroupDTO groupDTO);
    void addGroupMessage(GroupMessageDTO groupMessageDTO);
    void addGroupMember(GroupMemberDTO groupMemberDTO);
    List<GroupMessageDTO> selectGroupMessageByToUserSn(GroupMessageDTO groupMessageDTO);
    List<GroupMessageDTO> selectGroupMessageByFromUserSn(GroupMessageDTO groupMessageDTO);
    void updateGroupMessageStatus(GroupMessageDTO groupMessageDTO);
}
