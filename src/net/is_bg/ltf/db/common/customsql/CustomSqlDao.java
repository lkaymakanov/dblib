package net.is_bg.ltf.db.common.customsql;

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
		};
		execute(update, dataSource);
		ResultSetData data = new ResultSetData();
		ColumnMetaData meta = new ColumnMetaData();
		meta.setColumnName("Update Count:");
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
	
		
	
	static class DbExec extends DBExecutor{
		public DbExec(IConnectionFactory factory, boolean stlt) {
			super(factory);
			stealth = stlt;
		}
	}
}
