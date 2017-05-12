package dcnh.utils;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

public class ListStringTypeHandler implements TypeHandler<String[]> {

	@Override
	public void setParameter(PreparedStatement ps, int i, String[] parameter, JdbcType jdbcType) throws SQLException {
		StringBuilder sb = new StringBuilder();
		for (String str : parameter) {
			sb.append(str);
		}
		ps.setString(i, sb.toString());
	}

	@Override
	public String[] getResult(ResultSet rs, String columnName) throws SQLException {
		// TODO Auto-generated method stub
		return getArray(rs.getArray(columnName));
	}

	@Override
	public String[] getResult(ResultSet rs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return getArray(rs.getArray(columnIndex));
	}

	@Override
	public String[] getResult(CallableStatement cs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return getArray(cs.getArray(columnIndex));
	}

	private String[] getArray(Array array) {

		if (array == null) {
			return null;
		}

		try {
			return (String[]) array.getArray();
		} catch (Exception e) {
		}

		return null;
	}

}
