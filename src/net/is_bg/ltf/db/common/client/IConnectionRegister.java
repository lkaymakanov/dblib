package net.is_bg.ltf.db.common.client;

import java.sql.Connection;

public interface IConnectionRegister {
	
	public void add(Connection c);
	public Connection get(int hashcode);
	public void release(Connection c);
	public void release(int hashCode);
	public void release();
	public IConnectionRegister getConnectionRegister(String dsName);
	public void addConnectionRegister(String dsName);
}
