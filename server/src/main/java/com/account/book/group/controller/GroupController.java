package com.account.book.group.controller;

import com.account.book.group.dto.GroupDTO;
import com.account.book.group.message.dto.GroupMessageDTO;
import com.account.book.group.message.dto.GroupMessageStatus;
import com.account.book.group.service.GroupService;
import com.account.book.group.service.SearchType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/group")
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    public int createGroup( @RequestBody GroupDTO groupDTO ){
        groupService.createGroup( groupDTO );
        return groupDTO.getGroupSn();
    }

    @PostMapping("/message")
    public int sendMessage(@RequestBody GroupMessageDTO messageDTO){
        groupService.sendMessage( messageDTO );
        return messageDTO.getMessageSn();
    }

    @PutMapping("/message/status")
    public int updateStatusMessage(@RequestBody GroupMessageDTO messageDTO){
        groupService.updateMessageStatus( messageDTO );
        return messageDTO.getMessageSn();
    }

    @GetMapping("/message")
    public List<GroupMessageDTO> getMessage( GroupMessageDTO groupMessageDTO,
                                             @RequestParam String type ){
        return groupService.selectMessage(groupMessageDTO, SearchType.nameToType(type));
    }

}

