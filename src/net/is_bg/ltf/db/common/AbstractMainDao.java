package net.is_bg.ltf.db.common;
import java.io.Serializable;
import java.util.List;

import net.is_bg.ltf.db.common.interfaces.IConnectionFactory;


// TODO: Auto-generated Javadoc
/**
 * The Class AbstractMainDao.
 */
public  abstract class AbstractMainDao implements Serializable{
	
    private static final long serialVersionUID = -8039855905483886341L;

	/** The Constant SUCCESS. */
	protected static final String SUCCESS = "OK";
	
	/** The db executor. */
	protected DBExecutor dbExecutor;
	

	/**
	 * Instantiates a new abstract main dao.
	 *
	 * @param connectionFactory the connection factory
	 */
	public AbstractMainDao(IConnectionFactory connectionFactory) {
		this(new DBExecutor(connectionFactory));
	}
	
	public AbstractMainDao(DBExecutor executor) {
		dbExecutor = executor;
	} 
	/**
	 * Execute.
	 *
	 * @param statement the statement
	 */
	public final void execute(DBStatement statement) {
	    dbExecutor.execute(statement, DBExecutor.DEFAULT_TRANSACTION_ISOLATION_LEVEL);
	}
	
	
	/**
	 * Execute.
	 *
	 * @param statement the statement
	 */
	public final void execute(DBStatement statement, String dbREsourceName) {
	    dbExecutor.execute(statement, dbREsourceName, DBExecutor.DEFAULT_TRANSACTION_ISOLATION_LEVEL);
	}
	
	
	public final void execute(DBStatement [] statements, String dbREsourceName) {
	    dbExecutor.execute(statements, dbREsourceName, DBExecutor.DEFAULT_TRANSACTION_ISOLATION_LEVEL);
	}
	
	
	public final void execute(DBStatement [] statements, String dbREsourceName, int isolationLevel) {
	    dbExecutor.execute(statements, dbREsourceName, isolationLevel);
	}
	
	/**
	 * Execute.
	 *
	 * @param statement the statement
	 */
	public final void execute(DBStatement[] statement) {
	    dbExecutor.execute(statement,  DBExecutor.DEFAULT_TRANSACTION_ISOLATION_LEVEL);
	}
	
	/**
	 * Execute.
	 *
	 * @param dblist the dblist
	 */
	public final void execute(List<DBStatement> dblist){
		dbExecutor.execute(dblist.toArray(new DBStatement[0]), DBExecutor.DEFAULT_TRANSACTION_ISOLATION_LEVEL);
	}

	
	//Specify isolation level methods
	/**
	 * Execute.
	 *
	 * @param statement the statement
	 * @param isolationLevel the isolation level
	 */
	public final void execute(DBStatement statement, int isolationLevel) {
	    dbExecutor.execute(statement, isolationLevel);
	}

	/**
	 * Execute.
	 *
	 * @param statement the statement
	 * @param isolationLevel the isolation level
	 */
	public final void execute(DBStatement[] statement, int isolationLevel) {
		dbExecutor.execute(statement, isolationLevel);
	}
	
	
	/**
	 * Execute.
	 *
	 * @param dblist the dblist
	 * @param isolationLevel the isolation level
	 */
	public final void execute(List<DBStatement> dblist, int isolationLevel){
		dbExecutor.execute(dblist.toArray(new DBStatement[0]), isolationLevel);
	}

}
