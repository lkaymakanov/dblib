package net.is_bg.ltf.db.common.impl.visit;

import java.io.Serializable;
import java.util.Date;


public class VisitAdditionals implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6158661086536386219L;
	private long committedTransactionCnt = 0;
	private long rollBackedTransactionCnt = 0;
	private String userAgent;
	private Date lastActiveTime;
	private int transactionQueueMaxSize = 100;
	private long flags = 0; 
	private long lastRefreshTime;
	
	public long getLastRefreshTime() {
		return lastRefreshTime;
	}
	public void setLastRefreshTime(long lastRefreshTime) {
		this.lastRefreshTime = lastRefreshTime;
	}
	private Object data;
	
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public long getCommittedTransactionCnt() {
		return committedTransactionCnt;
	}
	public void setCommittedTransactionCnt(long committedTransactionCnt) {
		this.committedTransactionCnt = committedTransactionCnt;
	}
	public long getRollBackedTransactionCnt() {
		return rollBackedTransactionCnt;
	}
	public void setRollBackedTransactionCnt(long rollBackedTransactionCnt) {
		this.rollBackedTransactionCnt = rollBackedTransactionCnt;
	}
	public Date getLastActiveTime() {
		return lastActiveTime;
	}
	public void setLastActiveTime(Date lastActiveTime) {
		this.lastActiveTime = lastActiveTime;
	}
	public int getTransactionQueueMaxSize() {
		return transactionQueueMaxSize;
	}
	public void setTransactionQueueMaxSize(int transactionQueueMaxSize) {
		this.transactionQueueMaxSize = transactionQueueMaxSize;
	}
	public long getFlags() {
		return flags;
	}
	public void setFlags(long flags) {
		this.flags = flags;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
