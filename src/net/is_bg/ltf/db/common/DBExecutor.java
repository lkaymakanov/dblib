package net.is_bg.ltf.db.common;

import java.sql.Connection;
import java.sql.SQLException;

import net.is_bg.ltf.db.common.interfaces.IConnectionFactory;
import net.is_bg.ltf.db.common.interfaces.logging.ILog;


public class DBExecutor {

	private static ILog LOG = DBConfig.getDbLogFactory().getLog(DBExecutor.class);
	private IConnectionFactory factory;
	public static final int DEFAULT_TRANSACTION_ISOLATION_LEVEL  = Connection.TRANSACTION_READ_COMMITTED;

	public DBExecutor(IConnectionFactory factory) {
		super();
		this.factory = factory;
	}

	public void execute(DBStatement statement) throws JDBCException  {
		execute(new DBStatement[] { statement });
	}
	
	
	public void execute(DBStatement statement, int isolationLevel) throws JDBCException  {
		execute(new DBStatement[] { statement }, isolationLevel);
	}
	
	
	public void execute(DBStatement[] statements) {
		execute(statements, DEFAULT_TRANSACTION_ISOLATION_LEVEL);
	}
	
	
	

	public void execute(DBStatement[] statements, int isolationLevel) {
		Connection connection = factory.getConnection();
		
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