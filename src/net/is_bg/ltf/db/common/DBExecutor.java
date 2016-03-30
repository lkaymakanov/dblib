package net.is_bg.ltf.db.common;

import java.sql.Connection;
import java.sql.SQLException;

import net.is_bg.ltf.db.common.interfaces.IConnectionFactory;
import net.is_bg.ltf.db.common.interfaces.IConnectionFactoryX;
import net.is_bg.ltf.db.common.interfaces.logging.ILog;


// TODO: Auto-generated Javadoc
/**
 * The Class DBExecutor.
 */
public class DBExecutor {

	/** The log. */
	private static ILog LOG = DBConfig.getDbLogFactory().getLog(DBExecutor.class);
	
	/** The factory. */
	private IConnectionFactory factory;
	
	/** The Constant DEFAULT_TRANSACTION_ISOLATION_LEVEL. */
	public static final int DEFAULT_TRANSACTION_ISOLATION_LEVEL  = Connection.TRANSACTION_READ_COMMITTED;

	/**
	 * Instantiates a new dB executor.
	 *
	 * @param factory the factory
	 */
	public DBExecutor(IConnectionFactory factory) {
		super();
		this.factory = factory;
	}

	/**
	 * Execute.
	 *
	 * @param statement the statement
	 * @throws JDBCException the jDBC exception
	 */
	public void execute(DBStatement statement) throws JDBCException  {
		execute(new DBStatement[] { statement });
	}
	
	
	/**
	 * Execute.
	 *
	 * @param statement the statement
	 * @param isolationLevel the isolation level
	 * @throws JDBCException the jDBC exception
	 */
	public void execute(DBStatement statement, int isolationLevel) throws JDBCException  {
		execute(new DBStatement[] { statement }, isolationLevel);
	}
	
	
	/**
	 * Execute.
	 *
	 * @param statement the statement
	 * @param isolationLevel the isolation level
	 * @throws JDBCException the jDBC exception
	 */
	public void execute(DBStatement statement, String dbResourceName, int isolationLevel) throws JDBCException  {
		execute(new DBStatement[] { statement }, dbResourceName, isolationLevel);
	}
	
	
	/**
	 * Execute.
	 *
	 * @param statements the statements
	 */
	public void execute(DBStatement[] statements) {
		execute(statements, DEFAULT_TRANSACTION_ISOLATION_LEVEL);
	}
	
	
	/**
	 * Execute.
	 *
	 * @param statements the statements
	 * @param isolationLevel the isolation level
	 */
	public void execute(DBStatement[] statements, int isolationLevel) {
		execute(statements, null, isolationLevel);
	}

	/**
	 * Execute.
	 *
	 * @param statements the statements
	 * @param isolationLevel the isolation level
	 */
	public void execute(DBStatement[] statements, String dbResourceName, int isolationLevel) {
		Connection connection = dbResourceName == null  ?  factory.getConnection() :  (factory instanceof IConnectionFactoryX ? ((IConnectionFactoryX)factory).getConnection(dbResourceName) : factory.getConnection()) ;
		
		//log classes in transaction to console
		DbStatementLogUtilizer.printDBStatementClassesInTransaction(LOG, statements);
		try {
			connection.setTransactionIsolation(isolationLevel);
			connection.setAutoCommit(false);
		} catch (SQLException e2) {
			try {
				if (!connection.isClosed())
					connection.close();
			} catch (SQLException e) {
				DbStatementLogUtilizer.printExceptionToConsole(LOG, e);
			}
			DbStatementLogUtilizer.printExceptionToConsole(LOG,e2);
			throw new JDBCException(e2);
		}
		int i=0;
		try {
			for (; i < statements.length; i++) {
				statements[i].getDetails().setTransactionIsolationLevel(connection.getTransactionIsolation());
				statements[i].execute(connection);
				DbStatementLogUtilizer.printSqlStatement(LOG, statements[i]);  //print to console
			}
			connection.commit();
		} catch (Exception e) {
			try {
				DbStatementLogUtilizer.printErrSqlStatement(LOG, statements[i]);
				connection.rollback();
			} catch (SQLException e1) {
				//Log Exception on the verge of throw
				DbStatementLogUtilizer.printErrSqlStatement(LOG, statements[i]);
				throw new JDBCException(e1);
			}
			//Log Exception on the verge of throw
			DbStatementLogUtilizer.printErrSqlStatement(LOG, statements[i]);
			throw new JDBCException(e);
		} finally {			
			try {
				if (!connection.isClosed()){
					connection.close();
				}
			} catch (SQLException e) {
				//Log Exception on the verge of throw
				DbStatementLogUtilizer.printErrSqlStatement(LOG, statements[i]);
			}
		}
	}
}