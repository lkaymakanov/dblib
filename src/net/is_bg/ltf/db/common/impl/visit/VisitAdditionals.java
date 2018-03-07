package net.is_bg.ltf.db.common.impl.visit;

import java.util.Date;

public class VisitAdditionals {
	private long committedTransactionCnt = 0;
	private long rollBackedTransactionCnt = 0;
	private String userAgent;
	private Date lastActiveTime;
	private int transactionQueueMaxSize = 100;
	private long flags = VISIT_FLAGS.FILL_ROLLBACK_TR_QUEUE.val; 
	private Object data;
	
	//flag fields
	public boolean isShowId() {
		return !((flags & VISIT_FLAGS.SHOW_ID.val) == 0);
	}
	public void setShowId(boolean showId) {
		setFlag(VISIT_FLAGS.SHOW_ID, showId);
	}
	public boolean isLogUpdate() {
		return !((flags & VISIT_FLAGS.LOG_UPDATE.val) == 0);
	}
	public void setLogUpdate(boolean logUpdate) {
		setFlag(VISIT_FLAGS.LOG_UPDATE, logUpdate);
	}
	public boolean isLogDelete() {
		return !((flags & VISIT_FLAGS.LOG_DELETE.val) == 0);
	}
	public void setLogDelete(boolean logDelete) {
		setFlag(VISIT_FLAGS.LOG_DELETE, logDelete);
	}
	public boolean isLogInsert() {
		return !((flags & VISIT_FLAGS.LOG_INSERT.val) == 0);
	}
	public void setLogInsert(boolean logInsert) {
		setFlag(VISIT_FLAGS.LOG_INSERT, logInsert);
	}
	public boolean isLogSelect() {
		return !((flags & VISIT_FLAGS.LOG_SELECT.val) == 0);
	}
	public void setLogSelect(boolean logSelect) {
		setFlag(VISIT_FLAGS.LOG_SELECT, logSelect);
	}
	public boolean isUserOwnLogFile() {
		return !((flags & VISIT_FLAGS.USER_OWN_LOG_FILE.val) == 0);
	}
	public void setUserOwnLogFile(boolean userOwnLogFile) {
		setFlag(VISIT_FLAGS.USER_OWN_LOG_FILE, userOwnLogFile);
	}
	public boolean isFillTransactionQueue() {
		// TODO Auto-generated method stub
		return !((flags & VISIT_FLAGS.FILL_COMMITED_TR_QUEUE.val) == 0);
	}
	
	public void setFillTransactionQueue(boolean set) {
		// TODO Auto-generated method stub
		setFlag(VISIT_FLAGS.FILL_COMMITED_TR_QUEUE, set);
	}
	
	public boolean isFillRollbackedTransactionQueue() {
		// TODO Auto-generated method stub
		return !((flags & VISIT_FLAGS.FILL_ROLLBACK_TR_QUEUE.val) == 0);
	}
	
	public void setFillRollbackedTransactionQueue(boolean set) {
		// TODO Auto-generated method stub
		setFlag(VISIT_FLAGS.FILL_ROLLBACK_TR_QUEUE, set);
	}
	//end of flag fields
	
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
	
	
	
	/**
	 * Sets the corresponding flag to zero or one based on the boolean input parameter!
	 * @param flag
	 * @param set
	 */
	private void setFlag(VISIT_FLAGS flag, boolean set){
		if(set) flags = ( flags | flag.val);
		else  flags = (flags & (~flag.val));
	}
	
	/**
	 * Enumeration with visit flags used for
	 * @author lubo
	 *
	 */
	private enum VISIT_FLAGS{
		SHOW_ID(1 << 0),
		LOG_SELECT(1 << 1),
		LOG_INSERT(1 << 2),
		FILL_COMMITED_TR_QUEUE(1 << 3),
		FILL_ROLLBACK_TR_QUEUE(1 << 4),
		LOG_UPDATE(1 << 5),
		LOG_DELETE(1 << 6),
		USER_OWN_LOG_FILE(1 << 7);
		
		VISIT_FLAGS(long val){
			this.val = val;
		}
		
		private long val;
	}

	public int getTransactionQueueMaxSize() {
		// TODO Auto-generated method stub
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
	public void setData(Object data) {
		this.data = data;
	}
	public Object getData() {
		return data;
	}
}
