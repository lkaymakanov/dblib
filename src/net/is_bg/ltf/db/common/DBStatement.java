package net.is_bg.ltf.db.common;

import java.sql.Connection;

public interface DBStatement {
	public void execute(Connection connection) throws JDBCException;
	/**
	 * Get additional info like user, transaction, dbstatement execution time in milliseconds so forth
	 * 
	 * @return DBStatement details
	 */
	public DBStatementDetails getDetails();
	
}
