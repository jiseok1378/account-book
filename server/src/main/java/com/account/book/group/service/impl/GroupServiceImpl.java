package com.account.book.group.service.impl;

import com.account.book.group.dao.GroupDAO;
import com.account.book.group.dto.GroupDTO;
import com.account.book.group.member.dto.GlobalGroupRole;
import com.account.book.group.member.dto.GroupMemberDTO;
import com.account.book.group.message.dto.GroupMessageDTO;
import com.account.book.group.message.dto.GroupMessageStatus;
import com.account.book.group.service.GroupService;
import com.account.book.group.service.SearchType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GroupServiceImpl implements GroupService {

    private final GroupDAO groupDAO;

    @Override
    public void createGroup(GroupDTO groupDTO) {
        groupDAO.addGroup(groupDTO);
        GroupMemberDTO gm = new GroupMemberDTO();
        gm.setUserSn(groupDTO.getOwnerUserSn());
        gm.setGroupSn(groupDTO.getGroupSn());
        gm.setGroupRole(GlobalGroupRole.CREATOR.getRoleId());
        groupDAO.addGroupMember(gm);
    }

    @Override
    public void sendMessage(GroupMessageDTO groupMessageDTO) {
        groupDAO.addGroupMessage(groupMessageDTO);
    }

    @Override
    public void updateMessageStatus(GroupMessageDTO groupMessageDTO) {
        groupDAO.updateGroupMessageStatus(groupMessageDTO);
        /* 멤버 자동 등록 */
        if( GroupMessageStatus.getStatus(groupMessageDTO.getMessageStatus()) == GroupMessageStatus.ACCEPT ){
            GroupMemberDTO gm = new GroupMemberDTO();
            gm.setGroupRole(GlobalGroupRole.COMMON.getRoleId());
            gm.setUserSn(groupMessageDTO.getToUserSn());
            gm.setGroupSn(groupMessageDTO.getGroupSn());
            groupDAO.addGroupMember(gm);
        }
    }

    @Override
    public List<GroupMessageDTO> selectMessage(GroupMessageDTO groupMessageDTO, SearchType searchType) {

        switch (searchType){
            case TO:
                return groupDAO.selectGroupMessageByToUserSn(groupMessageDTO);
            case FROM:
            default:
                return groupDAO.selectGroupMessageByFromUserSn(groupMessageDTO);
        }
    }
}
