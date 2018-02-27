package net.is_bg.ltf.db.common;

import java.sql.Connection;

import net.is_bg.ltf.db.common.DBTransaction.DBTransactionBuilder;
import net.is_bg.ltf.db.common.interfaces.IConnectionFactory;
import net.is_bg.ltf.db.common.interfaces.IConnectionFactoryX;
import net.is_bg.ltf.db.common.interfaces.IDBTransaction;


// TODO: Auto-generated Javadoc
/**
 * The Class DBExecutor.
 */
public class DBExecutor {

	/** The log. */
	
	/** The factory. */
	private IConnectionFactory factory;

	protected boolean stealth;
	
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

	
	
	private  void execute(DBStatement[] statements, String dbResourceName, int isolationLevel) {
		Connection connection = dbResourceName == null  ?  factory.getConnection() :  (factory instanceof IConnectionFactoryX ? ((IConnectionFactoryX)factory).getConnection(dbResourceName) : factory.getConnection()) ;
		DBTransactionBuilder bd = 	new DBTransaction.DBTransactionBuilder(statements, connection, DBConfig.getDbLogFactory().getLog(DBExecutor.class)).setStealth(stealth);
		this.executeTransaction(bd.build(), dbResourceName, isolationLevel);
	}
	
	/**
	 * Handle db statements as one DBTransaction !!!
	 * @param transAction
	 * @param dbResourceName
	 * @param isolationLevel
	 */
	private void executeTransaction(IDBTransaction transAction, String dbResourceName, int isolationLevel){
		//execute transaction
		IDBTransaction trans = transAction;
		try{
			trans.setTransactionIsolation(isolationLevel);
			trans.setAutoCommit(false);
			trans.execute();   
			trans.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			trans.rollBack();
			throw DBTransaction.toJdbcException(e, null);
		}
		finally{
			trans.cleanUp();
		}
	}
	
	
	
}