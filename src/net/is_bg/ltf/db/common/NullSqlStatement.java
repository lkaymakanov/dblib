package net.is_bg.ltf.db.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**<pre>
 * Provide an Empty statement 
 * that represents 
 * NullObject!
 * Just fill with empty methods!
 </pre>
 */
public class NullSqlStatement extends SqlStatement{

	@Override
	public void execute(Connection connection) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected String getSqlString() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	protected void executeStatement(PreparedStatement prStmt)
			throws SQLException {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void setParameters(PreparedStatement prStmt) throws SQLException {
		// TODO Auto-generated method stub
	}

}
