package net.is_bg.ltf.db.common;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public abstract class SelectSqlStatement extends SqlStatement {

	@Override
	protected final void executeStatement(PreparedStatement prStmt) throws SQLException {
		ResultSet rs = prStmt.executeQuery();
		retrieveResult(rs);
	}

	protected void retrieveResult(ResultSet rs) throws SQLException {
		// no implementation
	}
}