package net.is_bg.ltf.db.common;

import java.sql.Connection;

/**
 * A class that handles several DBStatements as a single one!
 * @author lubo
 *
 */
public class DBStatementCompound implements DBStatement{
	
	DBStatement [] statements;
	
	public DBStatementCompound(DBStatement [] statements){
		this.statements = statements;
	}

	public void execute(Connection connection) throws JDBCException {
		// TODO Auto-generated method stub
		if(statements != null){
			for(int i = 0; i < statements.length; i++){
			   if(statements[i] != null) statements[i].execute(connection);
			}
		}
	}

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

}
