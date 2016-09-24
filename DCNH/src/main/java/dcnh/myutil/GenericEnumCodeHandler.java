package dcnh.myutil;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import dcnh.globalInfo.GenericEnum;

public class GenericEnumCodeHandler extends BaseTypeHandler<GenericEnum>{

	private final GenericEnum[] enums; 
	
	public GenericEnumCodeHandler(Class<GenericEnum> type) {  
		
        if (type == null)  {
            throw new IllegalArgumentException("Type argument cannot be null");  
         }
        this.enums = type.getEnumConstants();  
        if (this.enums == null)  {
            throw new IllegalArgumentException(type.getSimpleName()   + " does not represent an enum type.");  
        }
    } 
	
	@Override  
    public void setNonNullParameter(PreparedStatement ps, int i,  
            GenericEnum parameter, JdbcType jdbcType) throws SQLException {  
        ps.setInt(i, parameter.getCode());  
    }  
  
    @Override  
    public GenericEnum getNullableResult(ResultSet rs, String columnName)  
            throws SQLException {  
        int i = rs.getInt(columnName);  
        if (rs.wasNull()) {  
            return null;  
        } else {  
            return locateEnumStatus(i);  
        }  
  
    }  
  
    @Override  
    public GenericEnum getNullableResult(ResultSet rs, int columnIndex)  
            throws SQLException {  
        int i = rs.getInt(columnIndex);  
        if (rs.wasNull()) {  
            return null;  
        } else {  
            return locateEnumStatus(i);  
        }  
  
    }  
  
    @Override  
    public GenericEnum getNullableResult(CallableStatement cs, int columnIndex)  
            throws SQLException {  
        int i = cs.getInt(columnIndex);  
        if (cs.wasNull()) {  
            return null;  
        } else {  
            return locateEnumStatus(i);  
        }  
    } 
	
	private GenericEnum locateEnumStatus(int code) {  
        for (GenericEnum status : enums) {  
            if (status.getCode() == code) {  
                return status;  
            }  
        }  
        return null;  
    } 
}
