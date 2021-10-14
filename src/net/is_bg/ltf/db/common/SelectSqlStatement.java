package net.is_bg.ltf.db.common;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.is_bg.ltf.db.common.customsql.ColumnMetaData;
import net.is_bg.ltf.db.common.customsql.IResultSetData;

/**
 * The Class SelectSqlStatement.
 */
public abstract class SelectSqlStatement extends SqlStatement {

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.SqlStatement#executeStatement(java.sql.PreparedStatement)
	 */
	
	
	public SelectSqlStatement() {
		if(isValidStr()) resultSetType = storeResultsetStrategy.getResultSetType();
	}
	
	
	
	@Override
	protected final void executeStatement(PreparedStatement prStmt) throws SQLException {
		ResultSet rs = prStmt.executeQuery();
		if(resultSetMetaDataListener!=null) resultSetMetaDataListener.processMetaData(rs.getMetaData());
		retrieveResult(rs);
		if(isValidStr()) storeResultsetStrategy.getResult(rs, stprovider.getStorage(), stprovider.getStorage() == null? null:sqlForLog()); 
	}
	
	/**
	 * *
	 * Извличане на резултат след изпълнение на 'SelectSqlStatement'.
	 *
	 * @param rs the rs
	 * @throws SQLException the sQL exception
	 */
	protected void retrieveResult(ResultSet rs) throws SQLException {
		// no implementation
	}
	
	@Override
	public boolean isUpdate() {
		return false;
	}
	
	@Override
	public boolean isStoredProcedure() {
		return false;
	}
	

	
	public IResultSetData getStored() {
		return stored;
	}
}


class ResultSetData implements IResultSetData{

	private List<Object[]> res = new ArrayList<Object[]>();
	private List<ColumnMetaData> columndata = new ArrayList<ColumnMetaData>();
	private Exception exception;
	
	public ResultSetData(){
		
	}
	
	@Override
	public List<ColumnMetaData> getColumnMetaData() {
		return columndata;
	}

	@Override
	public List<Object[]> getResult() {
		return res;
	}

	@Override
	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	
}