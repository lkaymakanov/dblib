package net.is_bg.ltf.db.common.interfaces;

import java.io.Serializable;

public interface IDBTransaction extends Serializable{

	void setTransactionIsolation(int isolationLevel);

	void setAutoCommit(boolean b);

	void execute();

	void commit();

	void rollBack();

	void cleanUp();
	
}
