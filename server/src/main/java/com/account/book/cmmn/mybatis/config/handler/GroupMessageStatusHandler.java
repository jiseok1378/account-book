package com.account.book.cmmn.mybatis.config.handler;


import com.account.book.group.message.dto.GroupMessageStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(GroupMessageStatus.class)
public class GroupMessageStatusHandler implements TypeHandler<GroupMessageStatus> {
    @Override
    public void setParameter(PreparedStatement ps, int i, GroupMessageStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public GroupMessageStatus getResult(ResultSet rs, String columnName) throws SQLException {
        Integer code = rs.getInt(columnName);
        return GroupMessageStatus.getStatus( code );
    }

    @Override
    public GroupMessageStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        Integer code = rs.getInt(columnIndex);
        return GroupMessageStatus.getStatus( code );
    }

    @Override
    public GroupMessageStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
        Integer code = cs.getInt(columnIndex);
        return GroupMessageStatus.getStatus( code );
    }
}
