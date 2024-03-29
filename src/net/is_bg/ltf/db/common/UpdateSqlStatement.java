package net.is_bg.ltf.db.common;

import java.sql.PreparedStatement;
import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 * The Class UpdateSqlStatement.
 */
public abstract class UpdateSqlStatement extends SqlStatement {
	private int updateCnt = 0;
	
	@Override
	protected final void executeStatement(PreparedStatement prStmt) throws SQLException {
	   updateCnt =  prStmt.executeUpdate();
	   if(isValidStr()) storeResultsetStrategy.getResult(null, stprovider.getStorage(), stprovider.getStorage() == null? null:sqlForLog()); 
	}

	public int getUpdateCnt() {
		return updateCnt;
	}
	
	@Override
	public boolean isUpdate() {
		return true;
	}
	
	@Override
	public boolean isStoredProcedure() {
		return false;
	}
}
