package net.is_bg.ltf.db.common.interfaces;

import java.io.Serializable;

public interface ITransactionListener extends Serializable{
	public void beforeCommit(IDBTransaction transaction);
	public void afterCommit(IDBTransaction transaction);
	public void beforeRollBack(IDBTransaction transaction);
	public void afterRollBack(IDBTransaction transaction);
}
