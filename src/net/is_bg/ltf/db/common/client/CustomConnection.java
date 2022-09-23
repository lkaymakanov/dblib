package net.is_bg.ltf.db.common.client;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

class CustomConnection implements Connection{
	
	private int handle;
	private IConnectionOperation conop;
	private ICustomDSProperties customDsProp;
	private String dsName;
	
	@Override
	public int hashCode() {
		return handle;
	}
	
	CustomConnection(ICustomDSProperties prop){
	   this.customDsProp = prop;
	   this.conop = prop.getConop();
	   this.dsName = customDsProp.getDsName();
	   this.handle = customDsProp.getConop().getConnectionHandle(dsName);
	}
	

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

	@Override
	public void abort(Executor executor) throws SQLException {
		
	}

	@Override
	public void clearWarnings() throws SQLException {
		
	}

	@Override
	public void close() throws SQLException {
		conop.close(dsName, handle);
	}

	@Override
	public void commit() throws SQLException {
		conop.commit(dsName,handle);
	}

	@Override
	public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
		return null;
	}

	@Override
	public Blob createBlob() throws SQLException {
		return null;
	}

	@Override
	public Clob createClob() throws SQLException {
		return null;
	}

	@Override
	public NClob createNClob() throws SQLException {
		return null;
	}

	@Override
	public SQLXML createSQLXML() throws SQLException {
		return null;
	}

	@Override
	public Statement createStatement() throws SQLException {
		return null;
	}

	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
		return null;
	}

	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		return null;
	}

	@Override
	public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
		return null;
	}

	@Override
	public boolean getAutoCommit() throws SQLException {
		return false;
	}

	@Override
	public String getCatalog() throws SQLException {
		return null;
	}

	@Override
	public Properties getClientInfo() throws SQLException {
		return null;
	}

	@Override
	public String getClientInfo(String name) throws SQLException {
		return null;
	}

	@Override
	public int getHoldability() throws SQLException {
		return 0;
	}

	@Override
	public DatabaseMetaData getMetaData() throws SQLException {
		return conop.getDataBaseMetaData(dsName, handle);
	}

	@Override
	public int getNetworkTimeout() throws SQLException {
		return 0;
	}

	@Override
	public String getSchema() throws SQLException {
		return null;
	}

	@Override
	public int getTransactionIsolation() throws SQLException {
		return 0;
	}

	@Override
	public Map<String, Class<?>> getTypeMap() throws SQLException {
		return null;
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		return null;
	}

	@Override
	public boolean isClosed() throws SQLException {
		return false;
	}

	@Override
	public boolean isReadOnly() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isValid(int timeout) throws SQLException {
		return false;
	}

	@Override
	public String nativeSQL(String sql) throws SQLException {
		return null;
	}

	@Override
	public CallableStatement prepareCall(String sql) throws SQLException {
		PrepStatementAdditionalData adData = new PrepStatementAdditionalData();
		adData.sql = sql;
		CallableStatement c = new CustomCallableStatement(conop, adData, handle, dsName);
		return c;
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
		PrepStatementAdditionalData adData = new PrepStatementAdditionalData();
		adData.sql = sql;
		CallableStatement c = new CustomCallableStatement(conop, adData, handle, dsName);
		return c;
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
		PrepStatementAdditionalData adData = new PrepStatementAdditionalData();
		adData.sql = sql;
		CallableStatement c = new CustomCallableStatement(conop, adData, handle, dsName);
		return c;
	}

	@Override
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		PrepStatementAdditionalData adData = new PrepStatementAdditionalData();
		adData.sql = sql;
		PreparedStatement p = new CustomPreparedStatement(conop, adData, handle,  dsName);
		return p;
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
		PrepStatementAdditionalData adData = new PrepStatementAdditionalData();
		adData.sql = sql;
		PreparedStatement p = new CustomPreparedStatement(conop, adData, handle, dsName);
		return p;
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
		PrepStatementAdditionalData adData = new PrepStatementAdditionalData();
		adData.sql = sql;
		PreparedStatement p = new CustomPreparedStatement(conop, adData, handle, dsName);
		return p;
	}

	@Override
	public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
		PrepStatementAdditionalData adData = new PrepStatementAdditionalData();
		adData.sql = sql;
		PreparedStatement p = new CustomPreparedStatement(conop,adData,handle, dsName);
		return p;
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
		PrepStatementAdditionalData adData = new PrepStatementAdditionalData();
		adData.sql = sql;
		PreparedStatement p = new CustomPreparedStatement(conop, adData, handle, dsName);
		return p;
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
		PrepStatementAdditionalData adData = new PrepStatementAdditionalData();
		adData.sql = sql;
		PreparedStatement p = new CustomPreparedStatement(conop, adData, handle, dsName);
		return p;
	}

	@Override
	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
		
	}

	@Override
	public void rollback() throws SQLException {
		conop.rollBack(dsName,handle);
	}

	@Override
	public void rollback(Savepoint savepoint) throws SQLException {
		conop.rollBack(dsName, handle);
	}

	@Override
	public void setAutoCommit(boolean autoCommit) throws SQLException {
		conop.setAutoCommit(dsName, handle, autoCommit);
	}

	@Override
	public void setCatalog(String catalog) throws SQLException {
		
	}

	@Override
	public void setClientInfo(Properties properties) throws SQLClientInfoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setClientInfo(String name, String value) throws SQLClientInfoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHoldability(int holdability) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setReadOnly(boolean readOnly) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Savepoint setSavepoint() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Savepoint setSavepoint(String name) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSchema(String schema) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTransactionIsolation(int level) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	
	
}
