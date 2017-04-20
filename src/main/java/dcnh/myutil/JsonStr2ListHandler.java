package dcnh.myutil;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.json.JSONArray;

import net.minidev.json.JSONUtil;

public  class JsonStr2ListHandler extends BaseTypeHandler<List> {

	@Override
	public List getNullableResult(ResultSet arg0, String arg1) throws SQLException {
		// TODO Auto-generated method stub
		List<String> r = new ArrayList<>();
		//解析为list类型
		String[] items = arg0.getString(arg1).split(";");
		for(String str : items) {
			r.add(str);
		}
		return r;
	}

	@Override
	public List getNullableResult(ResultSet arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		List<String> r = new ArrayList<>();
		//解析为list类型
		String[] items = arg0.getString(arg1).split(";");
		for(String str : items) {
			r.add(str);
		}
		return r;
	}

	@Override
	public List getNullableResult(CallableStatement arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		List<String> r = new ArrayList<>();
		//解析为list类型
		String[] items = arg0.getString(arg1).split(";");
		for(String str : items) {
			r.add(str);
		}
		return r;
	}

	@Override
	public void setNonNullParameter(PreparedStatement arg0, int arg1, List arg2, JdbcType arg3) throws SQLException {
		// TODO Auto-generated method stub
		//List转JSON String
		StringBuilder jsonStrBuilder = new StringBuilder("");
		if(arg2 != null) {
			for(Object obj : arg2) {
				String participatorName = (String) obj;
				jsonStrBuilder.append(participatorName + ";");
			}
		}
		arg0.setString(arg1, jsonStrBuilder.toString());
	}

}
