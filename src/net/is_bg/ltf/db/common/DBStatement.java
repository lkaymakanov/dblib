package net.is_bg.ltf.db.common;

import java.sql.Connection;

// TODO: Auto-generated Javadoc
/**
 * The Interface DBStatement.
 */
public interface DBStatement {
	
	/**
	 * Изпълнява заявка към базата.
	 *
	 * @param connection the connection
	 * @throws JDBCException the jDBC exception
	 */
	public void execute(Connection connection) throws JDBCException;
	
	/**
	 * Get additional info like user, transaction, dbstatement execution time in milliseconds so forth.
	 *
	 * @return DBStatement details
	 */
	public DBStatementDetails getDetails();
	
	
	public boolean isUpdate();
	
	
	public boolean isStoredProcedure();
	
}
