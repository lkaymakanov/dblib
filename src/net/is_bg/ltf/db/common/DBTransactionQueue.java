package net.is_bg.ltf.db.common;

import java.util.LinkedList;

import net.is_bg.ltf.db.common.interfaces.IDBTransaction;

public class DBTransactionQueue<T extends IDBTransaction> {

	private LinkedList<T>  queue = new LinkedList<T>();
	private int maxSize =-1;
	private IMaxSizeProvider sizeprovider;
	
	
	public DBTransactionQueue(IMaxSizeProvider sizeprovider){
		this.sizeprovider = sizeprovider;
	}

	public LinkedList<T> getQueue() {
		return queue;
	}
	
	public  void addTransaction(T tranasction){
		queue.add(tranasction);
		maxSize = sizeprovider == null ? maxSize : sizeprovider.getMaxSixe();
		if(queue.size()  > maxSize) queue.removeFirst();
		
	} 
	
	public interface IMaxSizeProvider{
		public int getMaxSixe();
	}
	
	
}
