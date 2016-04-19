package net.is_bg.ltf.db.common.customsql;

import java.util.ArrayList;
import java.util.List;

public class ResultSetData implements IResultSetData{

	private List<Object[]> res = new ArrayList<Object[]>();
	private List<ColumnMetaData> columndata = new ArrayList<ColumnMetaData>();
	private Exception exception;
	
	public ResultSetData(){
		
	}
	
	@Override
	public List<ColumnMetaData> getColumMetaData() {
		// TODO Auto-generated method stub
		return columndata;
	}

	@Override
	public List<Object[]> getResult() {
		// TODO Auto-generated method stub
		return res;
	}

	@Override
	public Exception getException() {
		// TODO Auto-generated method stub
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}
	
	
	
}