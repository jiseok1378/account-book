package com.account.book.cmmn.config.handler;


import com.account.book.pair.dto.AcceptStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(AcceptStatus.class)
public class MessageTypeHandler implements TypeHandler<AcceptStatus> {
    @Override
    public void setParameter(PreparedStatement ps, int i, AcceptStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public AcceptStatus getResult(ResultSet rs, String columnName) throws SQLException {
        Integer code = rs.getInt(columnName);
        return AcceptStatus.getStatus( code );
    }

    @Override
    public AcceptStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        Integer code = rs.getInt(columnIndex);
        return AcceptStatus.getStatus( code );
    }

    @Override
    public AcceptStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
        Integer code = cs.getInt(columnIndex);
        return AcceptStatus.getStatus( code );
    }
}
