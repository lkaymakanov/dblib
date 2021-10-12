package net.is_bg.ltf.db.common;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.is_bg.ltf.db.common.customsql.ColumnMetaData;
import net.is_bg.ltf.db.common.customsql.IResultSetData;

/**
 * The Class SelectSqlStatement.
 */
public abstract class SelectSqlStatement extends SqlStatement {

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.SqlStatement#executeStatement(java.sql.PreparedStatement)
	 */
	private IStoreResultStrategy storeResultsetStrategy = null;
	private static volatile IResultSetStorageProvider stprovider = null;
	
	public SelectSqlStatement() {
		final SelectSqlStatement self = this;
		storeResultsetStrategy =  stprovider == null ? null :
		 new IStoreResultStrategy() {
			@Override
			public IResultSetData getResult(ResultSet rs, Map storage,  String sql) {
				if(storage == null) return null;
				IResultSetData resultSetData = new ResultSetData();
				
				try {
					ResultSetMetaData arg0 =  rs.getMetaData();
					int colcount = arg0.getColumnCount();
					for(int i =1 ; i <=colcount; i++){
						ColumnMetaData metadata = new ColumnMetaData();
						metadata.setColumnName(arg0.getColumnName(i));
						metadata.setColumnType(arg0.getColumnType(i));
						metadata.setCatalogName(arg0.getCatalogName(i));
						metadata.setDisplaySize(arg0.getColumnDisplaySize(i));
						metadata.setScale(arg0.getScale(i));
						metadata.setPrecision(arg0.getPrecision(i));
						metadata.setColumnLabel(arg0.getColumnLabel(i));
						metadata.setSchemaName( arg0.getSchemaName(i));
						metadata.setTableName(arg0.getTableName(i));
						resultSetData.getColumnMetaData().add(metadata);
					}
				
					if(rs.first()) {
						resultSetData.getResult().add(processRow(rs, resultSetData.getColumnMetaData()));
					}
					while (rs.next()) {
						resultSetData.getResult().add(processRow(rs, resultSetData.getColumnMetaData()));
					}
				}catch (SQLException e) {
					e.printStackTrace();
					return resultSetData;
				}
				
				
				ResultSetDataSql ressql = new ResultSetDataSql();
				ressql.className = self.getClass().getCanonicalName();
				ressql.resultSetData = resultSetData;
				ressql.sql = sql;
				
				long now = System.currentTimeMillis();
				storage.putIfAbsent("raw", new HashMap<Long,ResultSetDataSql>());
				Object raw = storage.get("raw");
				
				if(raw instanceof Map) {
					((Map) raw).put(now, ressql);
				}
				return resultSetData;
			}
			
			private   Object[] processRow(ResultSet rs, List<ColumnMetaData> meta) throws SQLException {
				int i =0;
				Object [] row = new Object[meta.size()];
				for(; i < meta.size(); i++){
					Object o = rs.getObject(i+1);
					row[i] = (o ==null ? null: o.toString());
				}
				return row;
			}
			
			@Override
			public int getResultSetConcurrency() {
				return  ResultSet.CONCUR_READ_ONLY;
			}
			
			@Override
			public int getResultSetType() {
				return ResultSet.TYPE_SCROLL_INSENSITIVE;
			}
		};
		if(isValidStr()) resultSetType = storeResultsetStrategy.getResultSetType();
		
	}
	
	private boolean isValidStr() {
		return (storeResultsetStrategy !=null && stprovider!=null && stprovider.getStorage()!=null);
	}
	
	@Override
	protected final void executeStatement(PreparedStatement prStmt) throws SQLException {
		ResultSet rs = prStmt.executeQuery();
		if(resultSetMetaDataListener!=null) resultSetMetaDataListener.processMetaData(rs.getMetaData());
		retrieveResult(rs);
		if(isValidStr()) storedResultSetData = storeResultsetStrategy.getResult(rs, stprovider.getStorage(), stprovider.getStorage() == null? null:sqlForLog()); 
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
	
	public static void setStprovider(IResultSetStorageProvider stprovider) {
		SelectSqlStatement.stprovider = stprovider;
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