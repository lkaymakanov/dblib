package net.is_bg.ltf.db.common.client;

class SqlData  implements ISqlQueryData{

	int connectionHandle;
	String sql;
	String stringRepOfParams, stringRepOfPrepStatementAdditionalData;
	String dsName;
	
	@Override
	public int getConnnectionHandle() {
		return connectionHandle;
	}

	@Override
	public String getSql() {
		return sql;
	}

	@Override
	public String getStringRepOfParams() {
		return stringRepOfParams;
	}

	@Override
	public String getStringRepOfPrepStatementAddionalData() {
		return stringRepOfPrepStatementAdditionalData;
	}
	
	@Override
	public String getDsName() {
		return dsName;
	}
	
}
