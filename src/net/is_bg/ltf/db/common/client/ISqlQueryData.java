package net.is_bg.ltf.db.common.client;

public interface ISqlQueryData {
	public int getConnnectionHandle();
	public String getSql();
	public String getDsName();
	public String getStringRepOfParams();
	public String getStringRepOfPrepStatementAddionalData();
}
