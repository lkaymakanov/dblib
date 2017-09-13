package net.is_bg.ltf.db.common;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.is_bg.ltf.db.common.interfaces.logging.ILog;


// TODO: Auto-generated Javadoc
/**
 * Details such as user, transactionNo, time of execution.
 *
 * @author lubo
 */
public  class DBStatementDetails implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2933268622449751406L;

	/** The Constant formatter. */
	private static final DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SSS");
	
	/** The user stuff. */
	private boolean loggable = true;
	
	/** The transaction isolation level. */
	private int transactionIsolationLevel = DBExecutor.DEFAULT_TRANSACTION_ISOLATION_LEVEL;

	/** The slq forlog. */
	private String  slqForlog = "";
	
	/** The user stuff. */
	private String userName = "";
	
	/** The tns. */
	private String tns = "";
	
	/** The transaction no. */
	private long transactionNo = 0;
	
	private Class statemetntClass;
	
	/** begin, end time of execution and duration. */
	long startTime,endTime, duration;
	
	/**
	 * begin, end time of execution and duration.
	 *
	 * @return the slq forlog
	 */
	public String getSlqForlog() {
		return slqForlog;
	}
	
	/**
	 * Sets the slq forlog.
	 *
	 * @param slqForlog the new slq forlog
	 */
	public void setSlqForlog(String slqForlog) {
		this.slqForlog = slqForlog;
	}
	
	/**
	 * Gets the start time.
	 *
	 * @return the start time
	 */
	public long getStartTime(){
		return startTime;
	}
	
	
	/**
	 * Gets the end time.
	 *
	 * @return the end time
	 */
	public long getEndTime(){
		return endTime;
	}
	
	/**
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public long getDuration(){
		return duration;
	}
	
	
	/**
	 * Gets the transaction isolation level.
	 *
	 * @return the transaction isolation level
	 */
	public int getTransactionIsolationLevel() {
		return transactionIsolationLevel;
	}
	
	/**
	 * Sets the transaction isolation level.
	 *
	 * @param transactionIsolationLevel the new transaction isolation level
	 */
	public void setTransactionIsolationLevel(int transactionIsolationLevel) {
		this.transactionIsolationLevel = transactionIsolationLevel;
	}
	
	/**
	 * The Enum ISOLATION_LEVEL.
	 */
	public enum ISOLATION_LEVEL{
			
			/** The transaction non. */
			TRANSACTION_NON(0),

		    /**
		     * A constant indicating that
		     * dirty reads, non-repeatable reads and phantom reads can occur.
		     * This level allows a row changed by one transaction to be read
		     * by another transaction before any changes in that row have been
		     * committed (a "dirty read").  If any of the changes are rolled back, 
		     * the second transaction will have retrieved an invalid row.
		     */
		    TRANSACTION_READ_UNCOMMITTED (1),

		    /**
		     * A constant indicating that
		     * dirty reads are prevented; non-repeatable reads and phantom
		     * reads can occur.  This level only prohibits a transaction
		     * from reading a row with uncommitted changes in it.
		     */
		    TRANSACTION_READ_COMMITTED (2),

		    /**
		     * A constant indicating that
		     * dirty reads and non-repeatable reads are prevented; phantom
		     * reads can occur.  This level prohibits a transaction from
		     * reading a row with uncommitted changes in it, and it also
		     * prohibits the situation where one transaction reads a row,
		     * a second transaction alters the row, and the first transaction
		     * rereads the row, getting different values the second time
		     * (a "non-repeatable read").
		     */
		    TRANSACTION_REPEATABLE_READ(4),

		    /**
		     * A constant indicating that
		     * dirty reads, non-repeatable reads and phantom reads are prevented.
		     * This level includes the prohibitions in
		     * <code>TRANSACTION_REPEATABLE_READ</code> and further prohibits the 
		     * situation where one transaction reads all rows that satisfy
		     * a <code>WHERE</code> condition, a second transaction inserts a row that
		     * satisfies that <code>WHERE</code> condition, and the first transaction
		     * rereads for the same condition, retrieving the additional
		     * "phantom" row in the second read.
		     */
		    TRANSACTION_SERIALIZABLE(8);

		
		/** The val. */
		int val;
		
		/**
		 * Instantiates a new isolation level.
		 *
		 * @param val the val
		 */
		ISOLATION_LEVEL(int val){
			this.val =val;
		};
		
		/**
		 * Gets the val.
		 *
		 * @return the val
		 */
		public long getval(){
			return val;
		}
		
		/**
		 * Val to isolation level.
		 *
		 * @param val the val
		 * @return the isolation level
		 */
		public static ISOLATION_LEVEL  valToIsolationLevel(int val){
			ISOLATION_LEVEL [] st = ISOLATION_LEVEL.values();
			for(int i = 0; i < st.length; i++){
				if(st[i].getval() == val) return st[i];
			}
			
			return ISOLATION_LEVEL.TRANSACTION_NON;
		}
	};
	
	

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * Gets the tns.
	 *
	 * @return the tns
	 */
	public String getTns() {
		return tns;
	}
	
	/**
	 * Sets the tns.
	 *
	 * @param tns the new tns
	 */
	public void setTns(String tns) {
		this.tns = tns;
	}
	
	/**
	 * Gets the transaction no.
	 *
	 * @return the transaction no
	 */
	public long getTransactionNo() {
		return transactionNo;
	}
	
	/**
	 * Sets the transaction no.
	 *
	 * @param transactionNo the new transaction no
	 */
	public void setTransactionNo(long transactionNo) {
		this.transactionNo = transactionNo;
	}
	
	/**
	 * Checks if is loggable.
	 *
	 * @return true, if is loggable
	 */
	public boolean isLoggable() {
		return loggable;
	}
	
	/**
	 * Sets the loggable.
	 *
	 * @param loggable the new loggable
	 */
	public void setLoggable(boolean loggable) {
		this.loggable = loggable;
	}

	/**
	 * Prints the details.
	 *
	 * @param LOG the log
	 * @return the string
	 */
	public String  printDetails(ILog LOG){
		if(!isLoggable()) return "";
		
		StringBuilder forLog = new StringBuilder();
		forLog.append("\nTransaction: " + transactionNo);
		forLog.append("\nStart Time:  " + formatter.format(new Date(getStartTime())));
		forLog.append("\nEnd Time:    " + formatter.format(new Date(getEndTime())));
		forLog.append("\nDuration:    " + getDuration() + " ms");
		forLog.append("\nApp User:    " + userName);
		forLog.append("\nDB URL:      " + tns);
		forLog.append("\nSQL:         " + getSlqForlog());
	    forLog.append("\n");
	    
		String st = forLog.toString();
		LOG.debug(st);
		return st;
	}

	public Class getStatemetntClass() {
		return statemetntClass;
	}

	public void setStatemetntClass(Class statemetntClass) {
		this.statemetntClass = statemetntClass;
	}
	
}
