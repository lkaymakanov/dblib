package net.is_bg.ltf.db.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


import net.is_bg.ltf.db.common.interfaces.logging.ILog;



// TODO: Auto-generated Javadoc
/**
 * The Class SqlStatement.
 */
public abstract class SqlStatement extends DBStatementAdapter {
	
	/** The Constant ddMMyyyyFormat. */
	public static final DateFormat ddMMyyyyFormat = new SimpleDateFormat("dd.MM.yyyy");
	
	/** The Constant LOG. */
	protected static final ILog LOG = DBConfig.getDbLogFactory().getLog(SqlStatement.class);
	
	/** The result set type. */
	protected int resultSetType = ResultSet.TYPE_FORWARD_ONLY;
	
	/** The result set concurrency. */
	protected int resultSetConcurrency = ResultSet.CONCUR_READ_ONLY; 
	
	
	/**Конструктор.*/
	public SqlStatement() {
		super();
	}

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.DBStatement#execute(java.sql.Connection)
	 */
	public void execute(Connection connection) {
		PreparedStatement prStmt = null;
		try {
			sql = getSqlString();
			prStmt = connection.prepareStatement(sql, resultSetType, resultSetConcurrency);//,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			setParameters(prStmt);
			executeStatement(prStmt);  //execute
		} catch (SQLException e) {
			throw new JDBCException(e);
		} finally {
			try {
				if (prStmt != null) {
					prStmt.close();
				}
				//get user details
				collectUserDetails();
			} catch (SQLException e) {
				LOG.error(e);
			}
		}
	}
	

	/**
	 * Абстрактен метод, който връща стринг за изпълнение.
	 *
	 * @return the sql string
	 */
	protected abstract String getSqlString();
	
	/**
	 * Абстрактен метод, който изпълнява PreparedStatement.
	 *
	 * @param prStmt the pr stmt
	 * @throws SQLException the sQL exception
	 */
	protected abstract void executeStatement(PreparedStatement prStmt) throws SQLException;

	/**
	 * Попълване на параметрите за заявката.
	 *
	 * @param prStmt the new parameters
	 * @throws SQLException the sQL exception
	 */
	protected void setParameters(PreparedStatement prStmt) throws SQLException {
		// no implementation
	}

	/**
	 * Sets the result set type.
	 *
	 * @param resultSetType the new result set type
	 */
	public void setResultSetType(int resultSetType) {
		this.resultSetType = resultSetType;
	}

	/**
	 * Sets the result set concurrency.
	 *
	 * @param resultSetConcurrency the new result set concurrency
	 */
	public void setResultSetConcurrency(int resultSetConcurrency) {
		this.resultSetConcurrency = resultSetConcurrency;
	}

}
