package net.is_bg.ltf.db.common.client;

import net.is_bg.ltf.db.common.customsql.IResultSetData;

public interface IConnectionOperation {
    
	public int    getConnectionHandle();
	public int    getConnectionHandle(String dsName);
	public void   setAutoCommit(String dsName,  int handle, boolean autoCommit);
	public IResultSetData exeSelect(ISqlQueryData data);
	public IResultSetData exeUpdate(ISqlQueryData data);
	public IResultSetData exeStoredProc(ISqlQueryData data);
	public void   commit(String dsName, int handle);
	public void   close(String dsName, int handle);
	public void   rollBack(String dsName,int handle);
	
}
