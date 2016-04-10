package net.is_bg.ltf.db.common;

import java.sql.PreparedStatement;
import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 * The Class UpdateSqlStatement.
 */
public abstract class UpdateSqlStatement extends SqlStatement {

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.SqlStatement#executeStatement(java.sql.PreparedStatement)
	 */
	private int updateCnt = 0;
	
	@Override
	protected final void executeStatement(PreparedStatement prStmt) throws SQLException {
	   updateCnt =  prStmt.executeUpdate();
	}

	public int getUpdateCnt() {
		return updateCnt;
	}
}
