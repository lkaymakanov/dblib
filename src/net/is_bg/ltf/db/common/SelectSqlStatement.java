package net.is_bg.ltf.db.common;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


// TODO: Auto-generated Javadoc
/**
 * The Class SelectSqlStatement.
 */
public abstract class SelectSqlStatement extends SqlStatement {

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.SqlStatement#executeStatement(java.sql.PreparedStatement)
	 */
	@Override
	protected final void executeStatement(PreparedStatement prStmt) throws SQLException {
		ResultSet rs = prStmt.executeQuery();
		if(resultSetMetaDataListener!=null) resultSetMetaDataListener.processMetaData(rs.getMetaData());
		retrieveResult(rs);
	}
	
	/**
	 * *
	 * Извличане на резултат след изпълнение на 'SelectSqlStatement'.
	 *
	 * @param rs the rs
	 * @throws SQLException the sQL exception
	 */
	protected void retrieveResult(ResultSet rs) throws SQLException {
		// no implementation
	}
}