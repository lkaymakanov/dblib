package net.is_bg.ltf.db.common;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import net.is_bg.ltf.db.common.interfaces.logging.ILog;


// TODO: Auto-generated Javadoc
/**
 * The Class StoredProcedure.
 */
public abstract class StoredProcedure extends DBStatementAdapter {
	
	/** The Constant LOG. */
	private static final ILog LOG = DBConfig.getDbLogFactory().getLog(StoredProcedure.class);// LogFactory.getLog(StoredProcedure.class);
	
	/** The Constant SUCCESS. */
	protected static final String SUCCESS = "OK";
	
	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.DBStatement#execute(java.sql.Connection)
	 */
	public void execute(Connection connection) {
		CallableStatement callableStatement = null;
		try {
			sql = getProcedureName();
			callableStatement = connection.prepareCall(sql);
			//execute procedure
			setParameters(callableStatement);
			callableStatement.executeUpdate();
			retrieveResult(callableStatement);
			if(isValidStr()) storeResultsetStrategy.getResult(null, stprovider.getStorage(), stprovider.getStorage() == null? null:sqlForLog()); 
		} catch (SQLException e) {
			throw new JDBCException(e);
		} finally {
			try {
				if (callableStatement != null)
					callableStatement.close();
				//get user details
				collectUserDetails();
			} catch (Exception e) {
				LOG.error(e);
			}
		}
	}

	/**
	 * Връща стринга с извикване на 'StoredProcedure'.
	 *
	 * @return the procedure name
	 */
	protected abstract String getProcedureName();

	/**
	 * *
	 * Попълва параметрите за 'StoredProcedure'.
	 *
	 * @param callableStatement the new parameters
	 * @throws SQLException the sQL exception
	 */
	protected abstract void setParameters(CallableStatement callableStatement) throws SQLException;

	/**
	 * *
	 * Извличане на резултат след изпълнение на 'StoredProcedure'.
	 *
	 * @param callableStatement the callable statement
	 * @throws SQLException the sQL exception
	 */
	protected void retrieveResult(CallableStatement callableStatement) throws SQLException {
		// no implementation
	}
	
	@Override
	public boolean isStoredProcedure() {
		return true;
	}
	
	@Override
	public boolean isUpdate() {
		return true;
	}
	
}
