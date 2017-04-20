package dcnh.myutil;



import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import dcnh.globalInfo.GenericEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GenericEnumNameHandler  extends BaseTypeHandler<GenericEnum>{
	private final GenericEnum[] enums;
	public GenericEnumNameHandler(Class<GenericEnum> type) {  
        if (type == null)  {
        	log.warn("type is null");
        	throw new IllegalArgumentException("Type argument cannot be null");  
        }
            
        this.enums = type.getEnumConstants();  
        if (this.enums == null)  {
        	log.warn("enums is null");
            throw new IllegalArgumentException(type.getSimpleName()  + " does not represent an enum type.");  
        }
    }  
  
    @Override  
    public void setNonNullParameter(PreparedStatement ps, int i,  
            GenericEnum parameter, JdbcType jdbcType) throws SQLException {  
        ps.setString(i, parameter.getName());  
    }  
  
    @Override  
    public GenericEnum getNullableResult(ResultSet rs, String columnName)  
            throws SQLException {  
        String name = rs.getString(columnName);  
        if (rs.wasNull()) {  
            return null;  
        } else {  
            return locateEnumStatus(name);  
        }  
  
    }  
  
    @Override  
    public GenericEnum getNullableResult(ResultSet rs, int columnIndex)  
            throws SQLException {  
        String name = rs.getString(columnIndex);  
        if (rs.wasNull()) {  
            return null;  
        } else {  
            return locateEnumStatus(name);  
        }  
  
    }  
  
    @Override  
    public GenericEnum getNullableResult(CallableStatement cs, int columnIndex)  
            throws SQLException {  
        String name = cs.getString(columnIndex);  
        if (cs.wasNull()) {  
            return null;  
        } else {  
            return locateEnumStatus(name);  
        }  
    }  
  
    private GenericEnum locateEnumStatus(String name) {  
        for (GenericEnum status : enums) {  
            if (status.getName().equals(name)) {  
                return status;  
            }  
        }  
        return null;  
    } 
}
