package net.is_bg.ltf.db.common;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.is_bg.ltf.db.common.ConnectionWrapper.ReporstSqlSatement;
import net.is_bg.ltf.db.common.customsql.ColumnMetaData;
import net.is_bg.ltf.db.common.customsql.IResultSetData;

class CommonStoreStrategy implements IStoreResultStrategy{
	private IResultSetStorageProvider stprovider = null;
	private Object self;
	
	CommonStoreStrategy(Object self, IResultSetStorageProvider st){
		this.stprovider = st;
		this.self = self;
	}
	
	@Override
	public IResultSetData getResult(ResultSet rs, Map storage,  String sql) {
		if(storage == null) return null;
		IResultSetData resultSetData = new ResultSetData();
		if(rs!=null) {
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
		}
		
		ResultSetDataSql ressql = new ResultSetDataSql();
		ressql.className = self.getClass().getCanonicalName();
		ressql.resultSetData = resultSetData;
		ressql.sql = sql;
		ressql.timeStamp = System.currentTimeMillis();
		if((stprovider.getF() & 1) !=0) ressql.stack = stackTrace();
		
		if(self instanceof SelectSqlStatement) ressql.type = 8;
		if(self instanceof UpdateSqlStatement) ressql.type = 16;
		if(self instanceof StoredProcedure) ressql.type = 32;
		if(self instanceof ReporstSqlSatement) ressql.type = 64;
		
		storage.putIfAbsent("raw", new ArrayList<ResultSetDataSql>());
		if(self instanceof DBStatementAdapter)  ((DBStatementAdapter)self).setStored(ressql);
		Object raw = storage.get("raw");
		
		if(raw instanceof List) {
			((List) raw).add(ressql);
		}
		return ressql;
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
	
	
	//show the calling methods info
		/**
		 * Call  StackTrace to print methods' frame where it's  invoked and where it' s located in source file.
		 *
		 * @param level the level
		 * @level parameter level shows how many levels down the stack to be printed. If no param all methods on stack are printed!
		 */
	static String stackTrace(int... level){
		StackTraceElement[] threadstacktrace =  Thread.currentThread().getStackTrace();
		String msg= "";
		StringBuilder  bd= new StringBuilder();
		if(threadstacktrace == null ) return msg;
		int l;
		 //show everything if no param
		if(level == null || level.length == 0)  l = threadstacktrace.length;  
		else {
			//else take the least of length and in parameter level
			l = threadstacktrace.length < level[0] ? threadstacktrace.length : level[0];
		}
		
		//print  stack trace
		if(threadstacktrace != null) {
			for(int i = 0; i < l; i++){
				if(threadstacktrace[i] == null)  continue;
				
				//get each stack element
				StackTraceElement el = threadstacktrace[i];
				
				//message to show
				msg  = String.format("%s.%s - line %d - file %s"  , el.getClassName(),  el.getMethodName(),  el.getLineNumber(), el.getFileName());
				bd.append(msg); bd.append("</br>");
			}
	     }
		
		return  bd.toString();
	}

}
