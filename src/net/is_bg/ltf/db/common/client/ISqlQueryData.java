package net.is_bg.ltf.db.common.client;

/***
 * <pre>
 * Represents a serialized form of all the data needed 
 * to execute SQL on a remote connection...
 * 
 * handle to remote connection
 * sql
 * remote dataSource name
 * string representation of params
 * string representation of additional data used for creating prep statement....
 * <pre>
 * @author lkaymakanov
 *
 */
public interface ISqlQueryData {
	public int getConnnectionHandle();
	public String getSql();
	public String getDsName();
	public String getStringRepOfParams();
	public String getStringRepOfPrepStatementAddionalData();
}
