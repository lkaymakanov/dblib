package net.is_bg.ltf.db.common.client;

import java.sql.Connection;

/***
 * <pre>
 * A connection register interface that keeps a connection for executing several sqls
 * on the same connection on the remote side...
 * 
 * ......................
 * <pre>
 * @author lkaymakanov
 *
 */
public interface IConnectionRegister {
	public void add(Connection c);
	public Connection get(int hashcode);
	public void release(Connection c);
	public void release(int hashCode);
	public void release();
	public IConnectionRegister getConnectionRegister(String dsName);
	public void addConnectionRegister(String dsName);
}
