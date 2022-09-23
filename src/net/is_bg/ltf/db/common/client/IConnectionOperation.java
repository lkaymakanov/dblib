package net.is_bg.ltf.db.common.client;

import java.sql.DatabaseMetaData;

import net.is_bg.ltf.db.common.customsql.IResultSetData;

/**
 * <pre>
 * Defines connection operations to be executed on remote Connection...
 * And a way to retrieve handle to remote connection...
 * <pre>
 * @author lkaymakanov
 *
 */
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
	public DatabaseMetaData getDataBaseMetaData(String dsName,int handle);
}
