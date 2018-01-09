package org.wah.doraemon.mybatis.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.wah.doraemon.consts.AccountState;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountStateHandler extends BaseTypeHandler<AccountState> implements TypeHandler<AccountState>{

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, AccountState parameter, JdbcType jdbcType) throws SQLException{
        ps.setInt(i, parameter.getId());
    }

    @Override
    public AccountState getNullableResult(ResultSet rs, String columnName) throws SQLException{
        return AccountState.getById(rs.getInt(columnName));
    }

    @Override
    public AccountState getNullableResult(ResultSet rs, int columnIndex) throws SQLException{
        return AccountState.getById(rs.getInt(columnIndex));
    }

    @Override
    public AccountState getNullableResult(CallableStatement cs, int columnIndex) throws SQLException{
        return AccountState.getById(cs.getInt(columnIndex));
    }
}
