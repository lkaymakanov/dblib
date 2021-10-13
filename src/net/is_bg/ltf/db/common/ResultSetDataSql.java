package net.is_bg.ltf.db.common;

import java.util.List;

import net.is_bg.ltf.db.common.customsql.ColumnMetaData;
import net.is_bg.ltf.db.common.customsql.IResultSetData;

class ResultSetDataSql implements IResultSetData {
	IResultSetData resultSetData;
	String sql;
	String className;
	long timeStamp;
	
	@Override
	public List<ColumnMetaData> getColumnMetaData() {
		return resultSetData == null ? null : resultSetData.getColumnMetaData();
	}
	@Override
	public List<Object[]> getResult() {
		return resultSetData == null ? null : resultSetData.getResult();
	}
	@Override
	public Exception getException() {
		return resultSetData == null ? null : resultSetData.getException();
	}
}
