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
import java.util.function.Function;

/***
 * <pre>
 * Aimed at wrapping remote connection and performing before functions....
 *    beforeCommit
 *    beforeRollback
 *    beforeClose
 *    <pre>
 * @author lkaymakanov
 *
 */
class ConnectionWrapperP implements Connection{
	private Connection wrappedConnecion;
	
	
	Function<Connection, Void> beforeCommit;
	Function<Connection, Void> beforeClose;
	Function<Connection, Void> beforeRollBack;
	
	ConnectionWrapperP(Connection con) {
		wrappedConnecion = con;
	}

	
   

	static ConnectionWrapperP wrap(Connection con){
		return  new ConnectionWrapperP(con);
	}
	
	@Override
	public int hashCode() {
		return wrappedConnecion.hashCode();
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return wrappedConnecion.unwrap(iface);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return wrappedConnecion.isWrapperFor(iface);
	}

	@Override
	public Statement createStatement() throws SQLException {
		return wrappedConnecion.createStatement();
	}

	@Override
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return wrappedConnecion.prepareStatement(sql);
	}

	@Override
	public CallableStatement prepareCall(String sql) throws SQLException {
		return wrappedConnecion.prepareCall(sql);
	}

	@Override
	public String nativeSQL(String sql) throws SQLException {
		return wrappedConnecion.nativeSQL(sql);
	}

	@Override
	public void setAutoCommit(boolean autoCommit) throws SQLException {
		wrappedConnecion.setAutoCommit(autoCommit);
	}

	@Override
	public boolean getAutoCommit() throws SQLException {
		return wrappedConnecion.getAutoCommit();
	}

	@Override
	public void commit() throws SQLException {
		if(beforeCommit!=null) beforeCommit.apply(this);
		wrappedConnecion.commit();
	}

	@Override
	public void rollback() throws SQLException {
		if(beforeRollBack!=null) beforeRollBack.apply(this);
		wrappedConnecion.rollback();
	}

	@Override
	public void close() throws SQLException {
		if(beforeClose!=null) beforeClose.apply(this);
		wrappedConnecion.close();
		
	}

	@Override
	public boolean isClosed() throws SQLException {
		return wrappedConnecion.isClosed();
	}

	@Override
	public DatabaseMetaData getMetaData() throws SQLException {
		return wrappedConnecion.getMetaData();
	}

	@Override
	public void setReadOnly(boolean readOnly) throws SQLException {
		wrappedConnecion.setReadOnly(readOnly);
	}

	@Override
	public boolean isReadOnly() throws SQLException {
		return wrappedConnecion.isReadOnly();
	}

	@Override
	public void setCatalog(String catalog) throws SQLException {
		wrappedConnecion.setCatalog(catalog);
	}

	@Override
	public String getCatalog() throws SQLException {
		return wrappedConnecion.getCatalog();
	}

	@Override
	public void setTransactionIsolation(int level) throws SQLException {
		wrappedConnecion.setTransactionIsolation(level);
	}

	@Override
	public int getTransactionIsolation() throws SQLException {
		return wrappedConnecion.getTransactionIsolation();
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		return wrappedConnecion.getWarnings();
	}

	@Override
	public void clearWarnings() throws SQLException {
		wrappedConnecion.clearWarnings();
	}

	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
		return wrappedConnecion.createStatement();
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
			throws SQLException {
		return wrappedConnecion.prepareStatement(sql, resultSetType, resultSetConcurrency);
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
		return wrappedConnecion.prepareCall(sql, resultSetType, resultSetConcurrency);
	}

	@Override
	public Map<String, Class<?>> getTypeMap() throws SQLException {
		return wrappedConnecion.getTypeMap();
	}

	@Override
	public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
		wrappedConnecion.setTypeMap(map);
	}

	@Override
	public void setHoldability(int holdability) throws SQLException {
		wrappedConnecion.setHoldability(holdability);
	}

	@Override
	public int getHoldability() throws SQLException {
		return wrappedConnecion.getHoldability();
	}

	@Override
	public Savepoint setSavepoint() throws SQLException {
		return wrappedConnecion.setSavepoint();
	}

	@Override
	public Savepoint setSavepoint(String name) throws SQLException {
		return wrappedConnecion.setSavepoint(name);
	}

	@Override
	public void rollback(Savepoint savepoint) throws SQLException {
		wrappedConnecion.rollback();
	}

	@Override
	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
		wrappedConnecion.releaseSavepoint(savepoint);
	}

	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		return wrappedConnecion.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
			int resultSetHoldability) throws SQLException {
		return wrappedConnecion.prepareStatement(sql, resultSetType, resultSetConcurrency);
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
			int resultSetHoldability) throws SQLException {
		return null;
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
		return wrappedConnecion.prepareStatement(sql,autoGeneratedKeys );
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
		return wrappedConnecion.prepareStatement(sql, columnIndexes);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
		return wrappedConnecion.prepareStatement(sql, columnNames);
	}

	@Override
	public Clob createClob() throws SQLException {
		return wrappedConnecion.createClob();
	}

	@Override
	public Blob createBlob() throws SQLException {
		return wrappedConnecion.createBlob();
	}

	@Override
	public NClob createNClob() throws SQLException {
		return wrappedConnecion.createNClob();
	}

	@Override
	public SQLXML createSQLXML() throws SQLException {
		return wrappedConnecion.createSQLXML();
	}

	@Override
	public boolean isValid(int timeout) throws SQLException {
		return wrappedConnecion.isValid(timeout);
	}

	@Override
	public void setClientInfo(String name, String value) throws SQLClientInfoException {
		wrappedConnecion.setClientInfo(name, value);
	}

	@Override
	public void setClientInfo(Properties properties) throws SQLClientInfoException {
		wrappedConnecion.setClientInfo(properties);
	}

	@Override
	public String getClientInfo(String name) throws SQLException {
		return wrappedConnecion.getClientInfo(name);
	}

	@Override
	public Properties getClientInfo() throws SQLException {
		return wrappedConnecion.getClientInfo();
	}

	@Override
	public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
		return wrappedConnecion.createArrayOf(typeName, elements);
	}

	@Override
	public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
		return wrappedConnecion.createStruct(typeName, attributes);
	}

	@Override
	public void setSchema(String schema) throws SQLException {
		wrappedConnecion.setSchema(schema);
	}

	@Override
	public String getSchema() throws SQLException {
		return wrappedConnecion.getSchema();
	}

	@Override
	public void abort(Executor executor) throws SQLException {
		wrappedConnecion.abort(executor);
	}

	@Override
	public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
		wrappedConnecion.setNetworkTimeout(executor, milliseconds);
	}	

	@Override
	public int getNetworkTimeout() throws SQLException {
		return wrappedConnecion.getNetworkTimeout();
	}
	
	
	/*static class ReporstSqlSatement extends NullSqlStatement{
		BindVariablePreparedStatementEx pstEx;
		IResultSetStorageProvider provider;
		ReporstSqlSatement(BindVariablePreparedStatementEx pstEx, IResultSetStorageProvider provider){
			this.pstEx = pstEx;
			this.provider = provider;
		}
		
		@Override
		protected String getSqlString() {
			return pstEx.getSqlForLog();
		}
		
		@Override
		public void execute(Connection connection) {
			super.execute(connection);
			getDetails().setSlqForlog(pstEx.getSqlForLog());
			if(isValidStr()) new CommonStoreStrategy(this, provider).getResult(null, provider == null ? null : provider.getStorage(), getSqlString());
		}
		
	}*/

}
