package net.is_bg.ltf.db.common.customsql;

import java.io.Serializable;

import net.is_bg.ltf.db.common.interfaces.IConnectionFactory;

public class CustomSqlConfigParams implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -882190139508047902L;
 	IConnectionFactory connectionFactory;
 	String dataSource;
 	int  rowBegin = -1;
 	int  rowEnd = -1;
 	boolean stealth;
	String sql;
 	
 	
	public IConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}
	public void setConnectionFactory(IConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	public int getRowBegin() {
		return rowBegin;
	}
	public void setRowBegin(int rowBegin) {
		this.rowBegin = rowBegin;
	}
	public int getRowEnd() {
		return rowEnd;
	}
	public void setRowEnd(int rowEnd) {
		this.rowEnd = rowEnd;
	}
	public boolean isStealth() {
		return stealth;
	}
	public void setStealth(boolean stealth) {
		this.stealth = stealth;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
 	
	
}
