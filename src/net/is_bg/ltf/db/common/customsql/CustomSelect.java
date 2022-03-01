package net.is_bg.ltf.db.common.customsql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import net.is_bg.ltf.db.common.client.ICustomParam;
import net.is_bg.ltf.db.common.client.IParamsSqlAdditionalData;
import net.is_bg.ltf.db.common.client.SqlTypeConverter;
import net.is_bg.ltf.db.common.interfaces.IAbstractModel;
import net.is_bg.ltf.db.common.interfaces.IResultSetMetaDataListener;
import net.is_bg.ltf.db.common.paging.SelectPagingSqlStatement;

class CustomSelect<T extends IAbstractModel> extends   SelectPagingSqlStatement<T> {
	
	private String sql;
	private ResultSetData resultSetData = new ResultSetData();
	private IParamsSqlAdditionalData params;
	private boolean convertDateToLong = false;
	
	private static class DefaultMetaDataListener implements IResultSetMetaDataListener{
		private ResultSetData resultSetData;
		private DefaultMetaDataListener(ResultSetData resultSetData){
			this.resultSetData = resultSetData;
		}
		
		@Override
		public Object processMetaData(ResultSetMetaData arg0) {
			try {
				int colcount = arg0.getColumnCount();
				for(int i=1; i <=colcount; i++){
					ColumnMetaData metadata = new ColumnMetaData();
					metadata.setColumnName(arg0.getColumnName(i));
					metadata.setColumnType(arg0.getColumnType(i));
				    metadata.setColumnTypeName(arg0.getColumnTypeName(i));
					metadata.setCatalogName(arg0.getCatalogName(i));
					metadata.setDisplaySize(arg0.getColumnDisplaySize(i));
					metadata.setScale(arg0.getScale(i));
					metadata.setPrecision(arg0.getPrecision(i));
					metadata.setColumnLabel(arg0.getColumnLabel(i));
					metadata.setSchemaName( arg0.getSchemaName(i));
					metadata.setTableName(arg0.getTableName(i));
					resultSetData.getColumnMetaData().add(metadata);
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
	
	
	public	CustomSelect(String sql){
		this.sql = sql;
		this.resultSetMetaDataListener = new DefaultMetaDataListener(resultSetData);
	}
	
	public	CustomSelect(String sql, IParamsSqlAdditionalData params, boolean convertDatToLong){
		this.sql = sql;
		this.params  = params;
		this.convertDateToLong = convertDatToLong;
		this.resultSetMetaDataListener = new DefaultMetaDataListener(resultSetData);
	}
	
	CustomSelect(String sql, IResultSetMetaDataListener metadatListener){
		this.sql = sql;
		this.resultSetMetaDataListener = metadatListener;
	}
	
	@Override
	protected void setParameters(PreparedStatement prStmt) throws SQLException {
		if(params != null) {
			for(ICustomParam p : params.getParams()){
				prStmt.setObject(p.getPosition(), p.getValue(), p.getSqlType());
			}
		}
	}
	
	@Override
	protected String getSqlString() {
		return rtnSqlString(sql); // markedText== null ? sqlText.getText() : markedText;
	}
	
	@Override
	protected void retrieveResult(ResultSet rs) throws SQLException {
		int i =0;
		while (rs.next()) {
			Object [] row = new Object[resultSetData.getColumnMetaData().size()];
			for(; i < resultSetData.getColumnMetaData().size(); i++){
				Object o = rs.getObject(i+1);
				row[i] = (o ==null ? null:( convertDateToLong ? SqlTypeConverter.toString(o) : o.toString()));
			}
			resultSetData.getResult().add(row);
			i = 0;
		}
	}

	public IResultSetData getResultSetData() {
		return resultSetData;
	}
	
}
