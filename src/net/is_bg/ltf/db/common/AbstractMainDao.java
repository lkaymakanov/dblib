package net.is_bg.ltf.db.common;
import java.util.List;

import net.is_bg.ltf.db.common.interfaces.IConnectionFactory;


public  abstract class AbstractMainDao {
	protected static final String SUCCESS = "OK";
	protected DBExecutor dbExecutor;
	

	public AbstractMainDao(IConnectionFactory connectionFactory) {
		dbExecutor = new DBExecutor(connectionFactory);
	}
	
	public final void execute(DBStatement statement) {
	    dbExecutor.execute(statement, DBExecutor.DEFAULT_TRANSACTION_ISOLATION_LEVEL);
	}
	
	public final void execute(DBStatement[] statement) {
	    dbExecutor.execute(statement,  DBExecutor.DEFAULT_TRANSACTION_ISOLATION_LEVEL);
	}
	
	public final void execute(List<DBStatement> dblist){
		dbExecutor.execute(dblist.toArray(new DBStatement[0]), DBExecutor.DEFAULT_TRANSACTION_ISOLATION_LEVEL);
	}

	
	//Specify isolation level methods
	public final void execute(DBStatement statement, int isolationLevel) {
	    dbExecutor.execute(statement, isolationLevel);
	}

	public final void execute(DBStatement[] statement, int isolationLevel) {
		dbExecutor.execute(statement, isolationLevel);
	}
	
	
	public final void execute(List<DBStatement> dblist, int isolationLevel){
		dbExecutor.execute(dblist.toArray(new DBStatement[0]), isolationLevel);
	}

}
