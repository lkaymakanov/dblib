package net.is_bg.ltf.db.common;

import java.io.BufferedReader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Map;



public interface IBindVariableData extends Serializable{

	public void setInt(Integer val,int pos);
	
	public int setInt(Integer val);
	
	public void setString(String str,int pos);
	
	public void setLong(Long val,int pos);
	
	public int setLong(Long val);
	
	public void setBigDecimal(BigDecimal val,int pos);
	
	public int setBigDecimal(BigDecimal val);
	
	public void setDate(Date val,int pos);
	
	public int setDate(Date val);
	
	public void setTime(Time val,int pos);
	
	public int setTime(Time val);
	
	public void setTimestamp(Timestamp val,int pos);
	
	public int setTimestamp(Timestamp val);
	
	public int setString(String str);
	
	/**
	 * Specify type explicitly with <code> setNull(int type)  </code>
	 */
	@Deprecated
	public void setNull();
	
	public void setNull(int type);
	
	public void setFloat(Float f);
	
	public void setDouble(Double d);
	
	public void setBoolean(boolean b);
	
	public void setObject(Object o);
	
	public void setObject(Object o,int pos);
	
	public void registerOutParameter(int type);
	
	public PreparedStatement setParameters(PreparedStatement prStmt) throws SQLException;
	
	public CallableStatement setParameters(CallableStatement callStmt) throws SQLException;
	
	public BufferedReader getClobAsBufferedReader(CallableStatement callStmt, int paramIndex);
	
	public BufferedReader getClobAsBufferedReader(ResultSet rs, int paramIndex);
	
	//TODO Wrap other setXXX methods in PreparedStatement
	public String sqlForLog(String sql);

	public Map<Integer, IBindVariableInfo> getValues();
	
	
	
}
