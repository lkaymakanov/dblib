package net.is_bg.ltf.db.common;

import java.sql.Connection;

// TODO: Auto-generated Javadoc
/**
 * A class that handles several DBStatements as a single one!.
 *
 * @author lubo
 */
public class DBStatementCompound implements DBStatement{
	
	/** The statements. */
	DBStatement [] statements;
	
	/**
	 * Instantiates a new dB statement compound.
	 *
	 * @param statements the statements
	 */
	public DBStatementCompound(DBStatement [] statements){
		this.statements = statements;
	}

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.DBStatement#execute(java.sql.Connection)
	 */
	public void execute(Connection connection) throws JDBCException {
		// TODO Auto-generated method stub
		if(statements != null){
			for(int i = 0; i < statements.length; i++){
			   if(statements[i] != null) statements[i].execute(connection);
			}
		}
	}

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.DBStatement#getDetails()
	 */
	public DBStatementDetails getDetails() {
		// TODO Auto-generated method stub
		DBStatementDetails [] d = new DBStatementDetails[statements.length];
		if(statements != null){
			for(int i = 0; i < statements.length; i++){
				if(statements[i] != null) d[i] = statements[i].getDetails();
			}
		}
		return new DBStatementDetailsCompound(d);
	}
	
	@Override
	public boolean isStoredProcedure() {
		return false;
	}
	
	@Override
	public boolean isUpdate() {
		return false;
	}
	
	

}
