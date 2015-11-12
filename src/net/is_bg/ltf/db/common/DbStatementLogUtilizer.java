package net.is_bg.ltf.db.common;

import net.is_bg.ltf.db.common.interfaces.logging.ILog;


public class DbStatementLogUtilizer {
	
	private static final String MSG_TRANSACTION_ROLLBACK_FORMAT_STRING = 	"Transaction rollbacked executing %s.\n" +
			"SQL:\t";
	private static final String MSG_DB_SATATEMENT_CLASS_NAME = 	"Class name  %s.";
	
	
	//logs classes in transaction to console
	public static void printDBStatementClassesInTransaction(ILog LOG, DBStatement[] statements){
		if(statements == null) return;
		
		LOG.debug("Transaction classes =====================================");
		for(int i = 0; i < statements.length ; i++){
			if(statements[i] == null) continue;
			LOG.debug(String.format(MSG_DB_SATATEMENT_CLASS_NAME, 
					statements[i].getClass().getName()));
		}
		LOG.debug("End of Transaction classes ===============================");
	}
	
	/**
	 * Print exception stack & Exception Message to console
	 * @param ex - Exception to be printed
	 */
	public static void printExceptionToConsole(ILog LOG, Exception ex){
		if(ex != null){
			LOG.debug("Exception Messsage =============================================\n" + ex.getMessage());
			LOG.debug("Exception StackTrace ===========================================\n");
			ex.printStackTrace();
			LOG.error(ex);
		}
	}
	
	
	/**
	 * Logs Slq statement when an error ocurrs
	 * @param LOG
	 * @param statements
	 */
	public static void printErrSqlStatement(ILog LOG, DBStatement[] statements){
		DBStatementDetails details;
		for(int i = 0; i < statements.length ; i++){
			if(statements[i] != null){
			    details = statements[i].getDetails();	
			    if(details != null){
			    	LOG.error(String.format(MSG_TRANSACTION_ROLLBACK_FORMAT_STRING, statements[i].getClass().getName()));
			    	printDetails(LOG, details);
			    }
			}
		}
	}
	
	/**
	 * Log the SQL statement
	 * @param LOG
	 * @param statements
	 */
	public static void printSqlStatement(ILog LOG, DBStatement[] statements){
		DBStatementDetails details;
		for(int i = 0; i < statements.length ; i++){
			if(statements[i] != null){
				details = statements[i].getDetails();
				printDetails(LOG, details);
			}
		}
	}
	

	public static void printErrSqlStatement(ILog LOG, DBStatement statement){
		DBStatement dbst  [] = new DBStatement[1];
		dbst[0] = statement;
		printErrSqlStatement(LOG, dbst);
	}
	
	public static void printSqlStatement(ILog LOG, DBStatement statement){
		DBStatement dbst  [] = new DBStatement[1];
		dbst[0] = statement;
		printSqlStatement(LOG, dbst);
	}
	
	
	private static void printDetails(ILog LOG, DBStatementDetails details){
		if(details == null) return;
		details.printDetails(LOG);
	}
	
	/**
	 * upperboundTime - max time in milliseconds
	 * @param LOG
	 * @param statement
	 * @param time
	 * @param upperboundTime
	 */
	public static void printTimeConsumingDBStatement(ILog LOG, DBStatement statement,  long upperboundTime){
		if(statement == null)  return;
		
		/*
		DBStatementDetails details = statement.getDetails();
		
	
		//if time is more than upeper time print to err & console
		if(statement.getDetails() >=upperboundTime){
			
			//print to stdout
			printSqlStatement(LOG,  statement);
			
			//print to stderr
			printErrSqlStatement(LOG,  statement);
			
		}*/
	}
		
}



