package net.is_bg.ltf.db.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 * <pre>
 * Provide an Empty statement
 * that represents
 * NullObject!
 * Just fill with empty methods!
 * </pre>.
 */
public class NullSqlStatement extends SqlStatement{

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.SqlStatement#execute(java.sql.Connection)
	 */
	@Override
	public void execute(Connection connection) {
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.SqlStatement#getSqlString()
	 */
	@Override
	protected String getSqlString() {
		// TODO Auto-generated method stub
		return "";
	}

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.SqlStatement#executeStatement(java.sql.PreparedStatement)
	 */
	@Override
	protected void executeStatement(PreparedStatement prStmt)
			throws SQLException {
		// TODO Auto-generated method stub
	}
	
	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.SqlStatement#setParameters(java.sql.PreparedStatement)
	 */
	@Override
	protected void setParameters(PreparedStatement prStmt) throws SQLException {
		// TODO Auto-generated method stub
	}

}
