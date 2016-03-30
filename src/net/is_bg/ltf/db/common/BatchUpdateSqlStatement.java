package net.is_bg.ltf.db.common;

import java.sql.PreparedStatement;
import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 * The Class UpdateSqlStatement.
 */
public abstract class BatchUpdateSqlStatement extends SqlStatement {

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.SqlStatement#executeStatement(java.sql.PreparedStatement)
	 */
	@Override
	protected final void executeStatement(PreparedStatement prStmt) throws SQLException {
	    prStmt.executeBatch();
	}
}
