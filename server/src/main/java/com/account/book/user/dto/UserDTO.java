package com.account.book.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class UserDTO implements Serializable {
    private Integer userSn;
    private String userId;
    private String userPw;
    private String userNm;
    private String userRfTk;
    private Integer loginFailedCnt;
    private Date firstCreateDate;
    private Boolean locked;
    private String firstCreateIp;
    private String lastLoginIp;
    private Date lastLoginDate;
}
