package com.account.book.group.dao;

import com.account.book.group.dto.GroupDTO;
import com.account.book.group.member.dto.GroupMemberDTO;
import com.account.book.group.message.dto.GroupMessageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface GroupDAO {
    /**
     * 그룹 생성
     * @param groupDTO
     */
    void addGroup(GroupDTO groupDTO);

    /**
     * 상대방에게 보낸 그룹 요청 메세지 저장
     * @param groupMessageDTO
     */
    void addGroupMessage(GroupMessageDTO groupMessageDTO);

    /**
     * 그룹 멤버에 초대 
     * 초대 기본 상태는 'PENDING' 상태임 (대기)
     * @param groupMemberDTO
     */
    void addGroupMember(GroupMemberDTO groupMemberDTO);

    /**
     * 내가 상대방에게 요청한 그룹 메세지 목록 조회
     * @param groupMessageDTO
     * @return
     */
    List<GroupMessageDTO> selectGroupMessageByToUserSn(GroupMessageDTO groupMessageDTO);

    /**
     * 내가 요청받은 그룹 메세지 목록 조회
     * @param groupMessageDTO
     * @return
     */
    List<GroupMessageDTO> selectGroupMessageByFromUserSn(GroupMessageDTO groupMessageDTO);

    /**
     * 상대방이 받은 그룹 메세지의 상태를 수정. (거부, 수락 등)
     * @param groupMessageDTO
     */
    void updateGroupMessageStatus(GroupMessageDTO groupMessageDTO);
}
