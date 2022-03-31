package net.is_bg.ltf.db.common.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

import net.is_bg.ltf.db.common.client.ICustomParam.CustomParamBuilder;

class CustomCallableStatement extends CustomPreparedStatement implements CallableStatement{

	
	CustomCallableStatement(IConnectionOperation conop, PrepStatementAdditionalData adData, int h, String dsName){
		super(conop, adData, h, dsName);
	}
	
	@Override
	public Array getArray(int parameterIndex) throws SQLException {
		return null;
	}

	@Override
	public Array getArray(String parameterName) throws SQLException {
		return null;
	}

	@Override
	public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
		return  customRes.getBigDecimal(parameterIndex);
	}
	
	
	@Override
	public int executeUpdate() throws SQLException {
		SqlData data = createInitSqlData();
		data.sql = additionalData.sql;
		additionalData.queryType = 1;
		fillParams(data, data.sql);
		res = conop.exeStoredProc(data);
		customRes = new CustomResultSet(res);
		customRes.next();
		return 0;
	}

	@Override
	public BigDecimal getBigDecimal(String parameterName) throws SQLException {
		return customRes.getBigDecimal(parameterName);
	}

	@Override
	public BigDecimal getBigDecimal(int parameterIndex, int scale) throws SQLException {
		return getBigDecimal(parameterIndex);
	}

	@Override
	public Blob getBlob(int parameterIndex) throws SQLException {
		return null;
	}

	@Override
	public Blob getBlob(String parameterName) throws SQLException {
		return null;
	}

	@Override
	public boolean getBoolean(int parameterIndex) throws SQLException {
		return customRes.getBoolean(parameterIndex);
	}

	@Override
	public boolean getBoolean(String parameterName) throws SQLException {
		return customRes.getBoolean(parameterName);
	}

	@Override
	public byte getByte(int parameterIndex) throws SQLException {
		return customRes.getByte(parameterIndex);
	}

	@Override
	public byte getByte(String parameterName) throws SQLException {
		return customRes.getByte(parameterName);
	}

	@Override
	public byte[] getBytes(int parameterIndex) throws SQLException {
		return customRes.getBytes(parameterIndex);
	}

	@Override
	public byte[] getBytes(String parameterName) throws SQLException {
		return customRes.getBytes(parameterName);
	}

	@Override
	public Reader getCharacterStream(int parameterIndex) throws SQLException {
		return null;
	}

	@Override
	public Reader getCharacterStream(String parameterName) throws SQLException {
		return null;
	}

	@Override
	public Clob getClob(int parameterIndex) throws SQLException {
		return null;
	}

	@Override
	public Clob getClob(String parameterName) throws SQLException {
		return null;
	}

	@Override
	public Date getDate(int parameterIndex) throws SQLException {
		return customRes.getDate(parameterIndex);
	}

	@Override
	public Date getDate(String parameterName) throws SQLException {
		return customRes.getDate(parameterName);
	}

	@Override
	public Date getDate(int parameterIndex, Calendar cal) throws SQLException {
		return getDate(parameterIndex);
	}

	@Override
	public Date getDate(String parameterName, Calendar cal) throws SQLException {
		return getDate(parameterName);
	}

	@Override
	public double getDouble(int parameterIndex) throws SQLException {
		return customRes.getDouble(parameterIndex);
	}

	@Override
	public double getDouble(String parameterName) throws SQLException {
		return customRes.getDouble(parameterName);
	}

	@Override
	public float getFloat(int parameterIndex) throws SQLException {
		return customRes.getFloat(parameterIndex);
	}

	@Override
	public float getFloat(String parameterName) throws SQLException {
		return customRes.getFloat(parameterName);
	}

	@Override
	public int getInt(int parameterIndex) throws SQLException {
		return customRes.getInt(parameterIndex);
	}

	@Override
	public int getInt(String parameterName) throws SQLException {
		return customRes.getInt(parameterName);
	}

	@Override
	public long getLong(int parameterIndex) throws SQLException {
		return customRes.getLong(parameterIndex);
	}

	@Override
	public long getLong(String parameterName) throws SQLException {
		return customRes.getLong(parameterName);
	}

	@Override
	public Reader getNCharacterStream(int parameterIndex) throws SQLException {
		return  getReader(customRes.getString(parameterIndex));
	}

	@Override
	public Reader getNCharacterStream(String parameterName) throws SQLException {
		return  getReader(customRes.getString(parameterName));
	}

	@Override
	public NClob getNClob(int parameterIndex) throws SQLException {
		return null;
	}

	@Override
	public NClob getNClob(String parameterName) throws SQLException {
		return null;
	}
	
	private static Reader getReader(String s) {
		final char [] c = s.toCharArray();
		Reader r=	new Reader() {
			@Override
			public int read(char[] cbuf, int off, int len) throws IOException {
				if(off + len  > c.length) return -1;
				int i=0;
				while(i < len) {
				   cbuf[i++] = 	c[off++];
				}
				return len;
			}
			
			@Override
			public void close() throws IOException {
				
			}
		};
		return r;
	}

	@Override
	public String getNString(int parameterIndex) throws SQLException {
		return customRes.getString(parameterIndex);
	}

	@Override
	public String getNString(String parameterName) throws SQLException {
		return customRes.getString(parameterName);
	}

	@Override
	public Object getObject(int parameterIndex) throws SQLException {
		return customRes.getObject(parameterIndex);
	}

	@Override
	public Object getObject(String parameterName) throws SQLException {
		return customRes.getObject(parameterName);
	}

	@Override
	public Object getObject(int parameterIndex, Map<String, Class<?>> map) throws SQLException {
		return customRes.getObject(parameterIndex);
	}

	@Override
	public Object getObject(String parameterName, Map<String, Class<?>> map) throws SQLException {
		return customRes.getObject(parameterName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getObject(int parameterIndex, Class<T> type) throws SQLException {
		return (T)customRes.getObject(parameterIndex);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getObject(String parameterName, Class<T> type) throws SQLException {
		return (T)customRes.getObject(parameterName);
	}

	@Override
	public Ref getRef(int parameterIndex) throws SQLException {
		return null;
	}

	@Override
	public Ref getRef(String parameterName) throws SQLException {
		return null;
	}

	@Override
	public RowId getRowId(int parameterIndex) throws SQLException {
		return null;
	}

	@Override
	public RowId getRowId(String parameterName) throws SQLException {
		return null;
	}

	@Override
	public SQLXML getSQLXML(int parameterIndex) throws SQLException {
		return null;
	}

	@Override
	public SQLXML getSQLXML(String parameterName) throws SQLException {
		return null;
	}

	@Override
	public short getShort(int parameterIndex) throws SQLException {
		return customRes.getShort(parameterIndex);
	}

	@Override
	public short getShort(String parameterName) throws SQLException {
		return customRes.getShort(parameterName);
	}

	@Override
	public String getString(int parameterIndex) throws SQLException {
		return customRes.getString(parameterIndex);
	}

	@Override
	public String getString(String parameterName) throws SQLException {
		return customRes.getString(parameterName);
	}

	@Override
	public Time getTime(int parameterIndex) throws SQLException {
		return customRes.getTime(parameterIndex);
	}

	@Override
	public Time getTime(String parameterName) throws SQLException {
		return customRes.getTime(parameterName);
	}

	@Override
	public Time getTime(int parameterIndex, Calendar cal) throws SQLException {
		return customRes.getTime(parameterIndex);
	}

	@Override
	public Time getTime(String parameterName, Calendar cal) throws SQLException {
		return customRes.getTime(parameterName, cal);
	}

	@Override
	public Timestamp getTimestamp(int parameterIndex) throws SQLException {
		return customRes.getTimestamp(parameterIndex);
	}

	@Override
	public Timestamp getTimestamp(String parameterName) throws SQLException {
		return customRes.getTimestamp(parameterName);
	}

	@Override
	public Timestamp getTimestamp(int parameterIndex, Calendar cal) throws SQLException {
		return customRes.getTimestamp(parameterIndex, cal);
	}

	@Override
	public Timestamp getTimestamp(String parameterName, Calendar cal) throws SQLException {
		
		return customRes.getTimestamp(parameterName);
	}

	@Override
	public URL getURL(int parameterIndex) throws SQLException {
		return customRes.getURL(parameterIndex);
	}

	@Override
	public URL getURL(String parameterName) throws SQLException {
		return customRes.getURL(parameterName);
	}

	@Override
	public void registerOutParameter(int parameterIndex, int sqlType) throws SQLException {
		ICustomParam.CustomParamBuilder bd = new CustomParamBuilder();
		bd.setPosition(parameterIndex); bd.setType(sqlType);
		bd.setInOutParam(ParamType.OUT);
		params.add(bd.build());
	}

	@Override
	public void registerOutParameter(String parameterName, int sqlType) throws SQLException {
		ICustomParam.CustomParamBuilder bd = new CustomParamBuilder();
		bd.setName(parameterName); bd.setType(sqlType);
		bd.setInOutParam(ParamType.OUT);
		params.add(bd.build());
		
	}

	@Override
	public void registerOutParameter(int parameterIndex, int sqlType, int scale) throws SQLException {
		registerOutParameter(parameterIndex, sqlType);
		
	}

	@Override
	public void registerOutParameter(int parameterIndex, int sqlType, String typeName) throws SQLException {
		registerOutParameter(parameterIndex, sqlType);
		
	}

	@Override
	public void registerOutParameter(String parameterName, int sqlType, int scale) throws SQLException {
		registerOutParameter(parameterName, sqlType);
	}

	@Override
	public void registerOutParameter(String parameterName, int sqlType, String typeName) throws SQLException {
		registerOutParameter(parameterName, sqlType);
	}

	@Override
	public void setAsciiStream(String parameterName, InputStream x) throws SQLException {
		
		
	}

	@Override
	public void setAsciiStream(String parameterName, InputStream x, int length) throws SQLException {
		
		
	}

	@Override
	public void setAsciiStream(String parameterName, InputStream x, long length) throws SQLException {
		
		
	}

	@Override
	public void setBigDecimal(String parameterName, BigDecimal x) throws SQLException {
		
		
	}

	@Override
	public void setBinaryStream(String parameterName, InputStream x) throws SQLException {
		
		
	}

	@Override
	public void setBinaryStream(String parameterName, InputStream x, int length) throws SQLException {
		
		
	}

	@Override
	public void setBinaryStream(String parameterName, InputStream x, long length) throws SQLException {
		
		
	}

	@Override
	public void setBlob(String parameterName, Blob x) throws SQLException {
		
		
	}

	@Override
	public void setBlob(String parameterName, InputStream inputStream) throws SQLException {
		
		
	}

	@Override
	public void setBlob(String parameterName, InputStream inputStream, long length) throws SQLException {
		
		
	}

	@Override
	public void setBoolean(String parameterName, boolean x) throws SQLException {
		
		
	}

	@Override
	public void setByte(String parameterName, byte x) throws SQLException {
		
		
	}

	@Override
	public void setBytes(String parameterName, byte[] x) throws SQLException {
		
		
	}

	@Override
	public void setCharacterStream(String parameterName, Reader reader) throws SQLException {
		
		
	}

	@Override
	public void setCharacterStream(String parameterName, Reader reader, int length) throws SQLException {
		
		
	}

	@Override
	public void setCharacterStream(String parameterName, Reader reader, long length) throws SQLException {
		
		
	}

	@Override
	public void setClob(String parameterName, Clob x) throws SQLException {
		
		
	}

	@Override
	public void setClob(String parameterName, Reader reader) throws SQLException {
		
		
	}

	@Override
	public void setClob(String parameterName, Reader reader, long length) throws SQLException {
		
		
	}

	@Override
	public void setDate(String parameterName, Date x) throws SQLException {
		
		
	}

	@Override
	public void setDate(String parameterName, Date x, Calendar cal) throws SQLException {
		
		
	}

	@Override
	public void setDouble(String parameterName, double x) throws SQLException {
		
		
	}

	@Override
	public void setFloat(String parameterName, float x) throws SQLException {
		
		
	}

	@Override
	public void setInt(String parameterName, int x) throws SQLException {
		
		
	}

	@Override
	public void setLong(String parameterName, long x) throws SQLException {
		
		
	}

	@Override
	public void setNCharacterStream(String parameterName, Reader value) throws SQLException {
		
		
	}

	@Override
	public void setNCharacterStream(String parameterName, Reader value, long length) throws SQLException {
		
		
	}

	@Override
	public void setNClob(String parameterName, NClob value) throws SQLException {
		
		
	}

	@Override
	public void setNClob(String parameterName, Reader reader) throws SQLException {
		
		
	}

	@Override
	public void setNClob(String parameterName, Reader reader, long length) throws SQLException {
		
		
	}

	@Override
	public void setNString(String parameterName, String value) throws SQLException {
		
		
	}

	@Override
	public void setNull(String parameterName, int sqlType) throws SQLException {
		
		
	}

	@Override
	public void setNull(String parameterName, int sqlType, String typeName) throws SQLException {
		
		
	}

	@Override
	public void setObject(String parameterName, Object x) throws SQLException {
		
		
	}

	@Override
	public void setObject(String parameterName, Object x, int targetSqlType) throws SQLException {
		
		
	}

	@Override
	public void setObject(String parameterName, Object x, int targetSqlType, int scale) throws SQLException {
		
		
	}

	@Override
	public void setRowId(String parameterName, RowId x) throws SQLException {
		
		
	}

	@Override
	public void setSQLXML(String parameterName, SQLXML xmlObject) throws SQLException {
		
		
	}

	@Override
	public void setShort(String parameterName, short x) throws SQLException {
		
		
	}

	@Override
	public void setString(String parameterName, String x) throws SQLException {
		
		
	}

	@Override
	public void setTime(String parameterName, Time x) throws SQLException {
		
	}

	@Override
	public void setTime(String parameterName, Time x, Calendar cal) throws SQLException {
		
		
	}

	@Override
	public void setTimestamp(String parameterName, Timestamp x) throws SQLException {
		
		
	}

	@Override
	public void setTimestamp(String parameterName, Timestamp x, Calendar cal) throws SQLException {
		
		
	}

	@Override
	public void setURL(String parameterName, URL val) throws SQLException {
		
		
	}

	@Override
	public boolean wasNull() throws SQLException {
		return false;
	}
	
}