package net.is_bg.ltf.db.common.customsql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import net.is_bg.ltf.db.common.interfaces.IAbstractModel;
import net.is_bg.ltf.db.common.interfaces.IResultSetMetaDataListener;
import net.is_bg.ltf.db.common.paging.SelectPagingSqlStatement;

public class CustomSelect<T extends IAbstractModel> extends   SelectPagingSqlStatement<T> {
	
	private String sql;
	
	private ResultSetData resultSetData = new ResultSetData();
	
	public	CustomSelect(String sql){
		this.sql = sql;
		this.resultSetMetaDataListener = new IResultSetMetaDataListener() {
			@Override
			public void processMetaData(ResultSetMetaData arg0) {
				// TODO Auto-generated method stub
				try {
					int colcount = arg0.getColumnCount();
					for(int i =1 ; i <=colcount; i++){
						ColumnMetaData metadata = new ColumnMetaData();
						metadata.setColumnName(arg0.getColumnName(i));
						metadata.setColumnType(arg0.getColumnType(i));
						metadata.setCatalogName(arg0.getCatalogName(i));
						metadata.setDisplaySize(arg0.getColumnDisplaySize(i));
						metadata.setPrecision(arg0.getPrecision(i));
						resultSetData.getColumMetaData().add(metadata);
					}
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}
	
	@Override
	protected String rtnSqlString(String sql) {
		// TODO Auto-generated method stub
		return super.rtnSqlString(getSqlString());
	}
	
	@Override
	protected String getSqlString() {
		// TODO Auto-generated method stub
		return sql;// markedText== null ? sqlText.getText() : markedText;
	}
	
	@Override
	protected void retrieveResult(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		int i =0;
		while (rs.next()) {
			Object [] row = new Object[resultSetData.getColumMetaData().size()];
			for(; i < resultSetData.getColumMetaData().size(); i++){
				Object o = rs.getObject(i+1);
				row[i] = (o ==null ? null: o.toString());
			}
			resultSetData.getResult().add(row);
			i = 0;
		}
	}

	public IResultSetData getResultSetData() {
		return resultSetData;
	}

	
	
	
}