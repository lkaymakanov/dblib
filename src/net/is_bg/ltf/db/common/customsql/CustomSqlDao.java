package net.is_bg.ltf.db.common.customsql;

import java.sql.Types;
import java.util.List;

import net.is_bg.ltf.db.common.AbstractMainDao;
import net.is_bg.ltf.db.common.DBConfig;
import net.is_bg.ltf.db.common.DBExecutor;
import net.is_bg.ltf.db.common.UpdateSqlStatement;
import net.is_bg.ltf.db.common.interfaces.IAbstractModel;
import net.is_bg.ltf.db.common.interfaces.IConnectionFactory;

public class CustomSqlDao extends AbstractMainDao{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6656168418142962121L;
	
	private CustomSqlDao(IConnectionFactory connectionFactory) {
		this(connectionFactory, true);
	}
	
	private CustomSqlDao(IConnectionFactory connectionFactory, boolean stlth){
		super(new DbExec(connectionFactory, stlth));
		
	}
	
	private IResultSetData performUpdate(String sql, String dataSource){
		final String sqll = sql;
		UpdateSqlStatement  update = new  UpdateSqlStatement() {
			@Override
			protected String getSqlString() {
				return sqll;
			}

			@Override
			public boolean isUpdate() {
				return true;
			}

			@Override
			public boolean isStoredProcedure() {
				return false;
			}
		};
		execute(update, dataSource);
		ResultSetData data = new ResultSetData();
		ColumnMetaData meta = new ColumnMetaData();
		meta.setColumnName("count");
		meta.setCatalogName("");
		meta.setColumnClassName("");
		meta.setColumnType(Types.BIGINT);
		meta.setDisplaySize(100);
		meta.setScale(0);
		meta.setSchemaName("");
		meta.setTableName("count");
		data.getColumnMetaData().add(meta);
		Object [] d = new Object [1];
		d[0] = update.getUpdateCnt();
		data.getResult().add(d);
		return data;
	}
	
	
	private IResultSetData performSelect(CustomSqlConfigParams cp){
		//create select statement 
		CustomSelect<IAbstractModel> select = new CustomSelect<IAbstractModel>(cp.sql);
		select.setRows(cp.getRowBegin(), cp.getRowEnd());
		execute(select, cp.dataSource);
		return select.getResultSetData();
	}
	
	private boolean isSelect(String sql){
		if(sql == null) return true;
		if(sql.trim().toLowerCase().startsWith("select")) return true;
		return false;
	}
	
	
	public static IResultSetData execSql(CustomSqlConfigParams configParams){
		CustomSqlDao cd = new CustomSqlDao(DBConfig.getConnectionFactory(), configParams.stealth);
		if(cd.isSelect(configParams.sql)) return cd.performSelect(configParams);
		else return cd.performUpdate(configParams.sql, configParams.dataSource);
	}
	
	public static IResultSetData execSelect(CustomSqlConfigParams configParams){
		CustomSqlDao cd = new CustomSqlDao(DBConfig.getConnectionFactory(), configParams.stealth);
		return cd.performSelect(configParams);
	}
	
	public static IResultSetData execUpdate(CustomSqlConfigParams configParams){
		CustomSqlDao cd = new CustomSqlDao(DBConfig.getConnectionFactory(), configParams.stealth);
		return cd.performUpdate(configParams.sql, configParams.dataSource);
	}
	
	
	public static IResultSetDataManager execSelectDataManager(CustomSqlConfigParams configParams){
		return getDataManager(execSelect(configParams));
	}
	
	public static IResultSetDataManager execUpdateDataManager(CustomSqlConfigParams configParams){
		return getDataManager(execUpdate(configParams));
	}
	
	
	public static IResultSetDataManager getDataManager(final IResultSetData data) {
		return new IResultSetDataManager() {
			int colCnt = (data == null  || data.getColumnMetaData() == null || data.getColumnMetaData().size() ==0)  ? 0: data.getColumnMetaData().size();
			int rowCnt = (data == null  || data.getResult() == null || data.getResult().size() ==0)  ? 0: data.getResult().size();
			
			@Override
			public int getRowsCnt() {
				return rowCnt;
			}
			
			
			@Override
			public IRowData getRowData(int rowIndex) {
				if(rowIndex < 0 || rowIndex >=rowCnt) return null;
				return new RowData(data.getResult().get(rowIndex));
			}
			
			
			@Override
			public ColumnMetaData getColumnMetaData(int colIndex) {
				return data == null ? null : (data.getColumnMetaData() == null? null : data.getColumnMetaData().get(colIndex));
			}
			
			@Override
			public int getColsCnt() {
				return colCnt;
			}


			@Override
			public List<ColumnMetaData> getColumnsMeataData() {
				return data == null ? null : data.getColumnMetaData();
			}


			@Override
			public List<Object[]> getRows() {
				return data == null ? null : data.getResult();
			}
		};
	}
	
	
	
	
	static class DbExec extends DBExecutor{
		public DbExec(IConnectionFactory factory, boolean stlt) {
			super(factory);
			stealth = stlt;
		}
	}
}
