package net.is_bg.ltf.db.common;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import net.is_bg.ltf.db.common.interfaces.logging.ILog;


public abstract class StoredProcedure extends DBStatementAdapter {
	private static final ILog LOG = DBConfig.getDbLogFactory().getLog(StoredProcedure.class);// LogFactory.getLog(StoredProcedure.class);
	
	protected static final String SUCCESS = "OK";
	
	public void execute(Connection connection) {
		CallableStatement callableStatement = null;
		try {
			sql = getProcedureName();
			callableStatement = connection.prepareCall(sql);
			setParameters(callableStatement);
			
			//get the start time of execution
			details.startTimer();
			
			//execute procedure
			callableStatement.executeUpdate();
			
			//get the end time of execution
			details.stopTimer();
			
			retrieveResult(callableStatement);
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

	protected abstract String getProcedureName();

	protected abstract void setParameters(CallableStatement callableStatement) throws SQLException;

	protected void retrieveResult(CallableStatement callableStatement) throws SQLException {
		// no implementation
	}
	
}
