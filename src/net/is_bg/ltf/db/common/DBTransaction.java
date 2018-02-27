
package net.is_bg.ltf.db.common;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


import net.is_bg.ltf.db.common.interfaces.IDBTransaction;
import net.is_bg.ltf.db.common.interfaces.ITransactionListener;
import net.is_bg.ltf.db.common.interfaces.logging.ILog;
import net.is_bg.ltf.db.common.interfaces.timer.IElaplsedTimer;
import net.is_bg.ltf.db.common.interfaces.visit.IVisit;



/***
 * A class that represents  sql transaction!
 * @author lubo
 *
 */
public class DBTransaction implements IDBTransaction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2110666118807504254L;
	private transient DBStatement [] statements;
	private transient Connection connection;
	private transient ILog  LOG;
	private int i = 0;
	private transient ITransactionListener listener;
	//a global transaction id!
	private static AtomicLong transactionIdSequence = new AtomicLong(0);
	private boolean rollBacked = false;
	private transient IVisit visit;
	private long transactionId;   //the id of transaction
	private transient IElaplsedTimer timer = DBConfig.getTimerFactory().getElapsedTimer();
	private transient IElaplsedTimer timerDbStatement = DBConfig.getTimerFactory().getElapsedTimer();
	static  AtomicLong commitedTransactions = new AtomicLong(0);     //the count of committed transactions
	static  AtomicLong rollbackedTranasactions = new AtomicLong(0);  //the count of rollbacked transactions
	private List<DBStatementDetails> dbStatementDetails = new ArrayList<DBStatementDetails>();
	private long startTime;
	private String tns;
	private String userName;
	private long endTime;
	private long duration;
	private int causedRollBack = -1;
	private long uId;
	private boolean stealth;
	
	
	private DBTransaction(DBStatement [] statements, Connection connection, ILog LOG, ITransactionListener transActionListener, boolean steatlh){
		this.statements = statements;
		this.connection = connection;
		this.transactionId = transactionIdSequence.incrementAndGet();
		this.listener = transActionListener;
		this.stealth = steatlh;
		this.LOG = LOG;
	}
	
	void setUpVisit(){
		visit = DBConfig.getVisitFactory().getVist();
		//user local transaction id
		if(visit != null){
			visit.setTransactionNo(visit.getTransactionNo() + 1);
			visit.getTransactionNo();
		}
	}
	
	public void execute() {
		setUpVisit();
		try{
			timer.start();
			startTime = timer.getStartTime();
			for (; i < statements.length; i++) {
				DBStatementDetails d = statements[i].getDetails();
				statements[i].getDetails().setTransactionIsolationLevel(connection.getTransactionIsolation());
				timerDbStatement.start();
				d.startTime = timerDbStatement.getStartTime();
				statements[i].execute(connection);
				timerDbStatement.stop();
				/*if((i+1) % 4 == 0){
					throw new RuntimeException("deliberate exception"); 
				}*/
				d.endTime = timerDbStatement.getEndTime();
				d.duration = d.endTime - d.startTime;
			}
			timer.stop();
			endTime = timer.getEndTime();
			duration = endTime - startTime;
			LOG.debug(getTrInfoForLog(null));
		}
		catch (Exception e) {
			String message= null;
			if(statements != null && statements.length > 0) {
				causedRollBack = i;
				LOG.debug(getTrInfoForLog(exceptionToString(e)));
			}
			// TODO: handle exception
			throw toJdbcException(e, message);
		}
	}
	
	public void commit(){
		try {
			connection.commit();
			commitedTransactions.incrementAndGet();
			if(listener!=null)listener.afterCommit(this);
			//if(visit != null) visit.setCommittedTransactionCnt(visit.getCommittedTransactionCnt() + 1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw toJdbcException(e, null);
		}
	}
	

	public void rollBack(){
		try {
			//if(visit != null) visit.setRollBackedTransactionCnt(visit.getRollBackedTransactionCnt() + 1);
			connection.rollback();
			rollbackedTranasactions.incrementAndGet();
			timer.stop();
			endTime = timer.getEndTime();
			duration = endTime - startTime;
			if(listener!=null)listener.afterRollBack(this);
			rollBacked = true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw toJdbcException(e, null);
		}
	}
	

	public void setTransactionIsolation(int isolationLevel){
		try {
			//connection.setTransactionIsolation(isolationLevel);
		} catch (Exception e) {
			e.printStackTrace();
			throw toJdbcException(e, null);
		}
	}
	
	public void setAutoCommit(boolean autoCommit){
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
			throw  toJdbcException(e, null);
		}
	}
	
	public void cleanUp(){
		try {
			if(!connection.isClosed()) connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			DbStatementLogUtilizer.printErrSqlStatement(LOG, statements[i]);
		}
	}

	
	private DBTransactionInfo getTrInfo(String exceptionMessage){
		DBTransactionInfo trInfo = new DBTransactionInfo();
		trInfo.glTrNo = transactionId;
		trInfo.com = true;
		if(visit!=null){
			trInfo.tns = visit.getTns();
			trInfo.uTrNo = visit.getTransactionNo();
			trInfo.uName = visit.getFullName();
			trInfo.uId = uId;
		}
		tns = trInfo.tns;
		userName = trInfo.uName;
		trInfo.sT = timer.getStartTime();
		trInfo.eT = timer.getEndTime();
		trInfo.d = timer.getDuration();
		
		List<DBTransactionStatementInfo> dbTrInfoStatements = new ArrayList<DBTransactionStatementInfo>();
		for(DBStatement db : statements){
			DBStatementDetails d = db.getDetails();
			DBTransactionStatementInfo st = new DBTransactionStatementInfo();
			dbStatementDetails.add(d);
			st.cl = db.getClass().toString();
			st.sT = d.startTime;
			st.eT = d.getEndTime();
			st.sql = d.getSlqForlog();
			dbTrInfoStatements.add(st);
		}
		trInfo.setSt(dbTrInfoStatements);
		trInfo.stCnt = statements.length;
		if(causedRollBack > -1){
			trInfo.clCausedEx = statements[causedRollBack].getClass().toString();
			trInfo.ex = exceptionMessage;
			trInfo.com = false;
			trInfo.indEx = causedRollBack;
		}
		return trInfo;
	}
	
	private String getTrInfoForLog(String exceptionMessage){
		return getTrInfoForLog(exceptionMessage , false);
	}
	
	private String getTrInfoForLog(String exceptionMessage,  boolean json){
		return getTrInfoForLog( exceptionMessage,  statements,  json);
	}
	
	
	private  String  getTrInfoForLog(String exceptionMessage, DBStatement[]  statements, boolean json){
		DBTransactionInfo trInfo = getTrInfo(exceptionMessage);
		StringBuilder sbclasses = new StringBuilder();
		StringBuilder sSqls = new StringBuilder();
		String s = "";
		if(stealth) return s;
		if(!json){
			sbclasses.append(String.format(DBTransactionMarkConstants.MAGICST_TRANS_BEG_STARTTIME, new Date(timer.getStartTime())).toString());
			sbclasses.append("\n");
			sbclasses.append(DBTransactionMarkConstants.TRANSACTION_ID +  trInfo.glTrNo);
			sbclasses.append("\n");
			sbclasses.append(DBTransactionMarkConstants.STATEMENT_COUNT +  statements.length);
			sbclasses.append("\n");
			sbclasses.append(DBTransactionMarkConstants.USER_NAME + trInfo.uName);
			sbclasses.append("\n");
			sbclasses.append(DBTransactionMarkConstants.USER_ID + trInfo.uId);
			sbclasses.append("\n");
			sbclasses.append(DBTransactionMarkConstants.USER_TRNO + (visit == null ? 0 : visit.getTransactionNo()));
			sbclasses.append("\n");
			sbclasses.append(DBTransactionMarkConstants.DURATION + timer.getDuration());
			sbclasses.append("\n");
			sbclasses.append(DBTransactionMarkConstants.DB_URL + trInfo.tns);
			sbclasses.append("\n");
			if(exceptionMessage != null){
				sbclasses.append(DBTransactionMarkConstants.EXCEPTION_BEGIN);
				sbclasses.append("\n");
				sbclasses.append(exceptionMessage);
				sbclasses.append("\n");
				sbclasses.append(DBTransactionMarkConstants.EXCEPTION_END);
				sbclasses.append("\n");
			}
			sbclasses.append(DBTransactionMarkConstants.CLASSES);
			sbclasses.append("\n");
			sSqls.append(DBTransactionMarkConstants.SQLS);
			int len = (causedRollBack > 0) ? causedRollBack: statements.length;   //up the the statement that cused exception if no exception take the whole statement array
			for(int i=0; i < len; i++){
				DBStatement db = statements[i];
				DBStatementDetails d = db.getDetails();
				sbclasses.append(db.getClass());
				sbclasses.append("\n");
				sSqls.append("\n");
				sSqls.append(DBTransactionMarkConstants.STATEMENT_BEGIN);   //mark up begin of statement 
				sSqls.append("\n");
				sSqls.append(d.getSlqForlog());
				sSqls.append("\n");
				sSqls.append(DBTransactionMarkConstants.START_TIME + d.getStartTime());
				sSqls.append("\n");
				sSqls.append(DBTransactionMarkConstants.END_TIME + d.getEndTime());
				sSqls.append("\n");
				sSqls.append(DBTransactionMarkConstants.DURATION + d.getDuration());
				sSqls.append("\n");
				sSqls.append(DBTransactionMarkConstants.STATEMENT_END);   //mark up end of statement
			}
			sSqls.append("\n");
			sbclasses.append(sSqls.toString());
			sSqls.append("\n");
			sbclasses.append(DBTransactionMarkConstants.TRANSACTION_END);
			sSqls.append("\n");
			s = sbclasses.toString();
			//s = sbclasses.append(String.format(DBTransactionMarkConstatns.MAGICST_TRANS_BEG_STARTTIME, new Date(timer.getStartTime())).toString()).toString();
		}else{
			try {
				//s = om.writeValueAsString(trInfo);
				//s = String.format(DBTransactionMarkConstants.MAGICST_TRANS_BEG_STARTTIME, new Date(timer.getStartTime())) + s + String.format(DBTransactionMarkConstants.TRANSACTION_END_ENDTIME, new Date(timer.getStartTime())).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return s;
	}
	


	

	public long getStartTime() {
		return startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public long getDuration() {
		return duration;
	}

	public List<DBStatementDetails> getDbStatementDetails(){
		return dbStatementDetails;
	}
	
	public  long getTransactionId() {
		return transactionId;
	}

	public boolean isRollBacked() {
		return rollBacked;
	}
	public String getTns() {
		return tns;
	}
	public String getUserName() {
		return userName;
	}


	public static class DBTransactionBuilder {
		DBStatement [] statements; Connection connection; ILog LOG;
		ITransactionListener transActionListener = DBConfig.getTransactionListener();
		boolean stealth;
		public DBTransactionBuilder (DBStatement [] statements, Connection connection, ILog LOG){
			this.statements = statements;
			this.connection = connection;
			this.LOG = LOG;
		}
		
		public DBTransactionBuilder setStealth(boolean stealth) {
			this.stealth = stealth;
			return this;
		}
		
		public IDBTransaction build(){
			return new DBTransaction(statements, connection, LOG, transActionListener, stealth);
		}
	}
	
	public static JDBCException toJdbcException(Exception ex, String message){
		String msg = ex.getMessage();
	    msg = msg == null ? ("\n" + exceptionToString(ex)) : (msg+ "\n" +  exceptionToString(ex));
		JDBCException jdbcex = new JDBCException(message == null ? "" :message, ex);
		return jdbcex;
	}
	
	
	private static String exceptionToString(Exception e){
		if(e== null) return "";
		return  ( e.toString() + e.getMessage()) == null ? "" : " " +  e.getCause() + getStackTrace(e);
	}
	
	
	public static String   getStackTrace(Exception ex){
		if(ex == null) return "";
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		return sw.toString(); 
	}

	
	static class DBTransactionInfo implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 5242506415676406977L;
		long glTrNo;
		long uTrNo;
		long sT;
		long eT;
		long d;
		List<DBTransactionStatementInfo> st;
		long uId;
		String uName;
		boolean com;
		String ex;
		String tns;
		String clCausedEx;
		Integer indEx;
		int stCnt;

		public DBTransactionInfo(){
			
		}
		

		public long getGlTrNo() {
			return glTrNo;
		}
		public void setGlTrNo(long glTrNo) {
			this.glTrNo = glTrNo;
		}
		public long getuTrNo() {
			return uTrNo;
		}
		public void setuTrNo(long uTrNo) {
			this.uTrNo = uTrNo;
		}
		public long getsT() {
			return sT;
		}
		public void setsT(long sT) {
			this.sT = sT;
		}
		public long geteT() {
			return eT;
		}
		public void seteT(long eT) {
			this.eT = eT;
		}
		public long getD() {
			return d;
		}
		public void setD(long d) {
			this.d = d;
		}
		public List<DBTransactionStatementInfo> getSt() {
			return st;
		}
		public void setSt(List<DBTransactionStatementInfo> statements) {
			this.st = statements;
		}
		public long getuId() {
			return uId;
		}
		public void setuId(long uId) {
			this.uId = uId;
		}
		public String getuName() {
			return uName;
		}
		public void setuName(String uName) {
			this.uName = uName;
		}
		public boolean isCom() {
			return com;
		}
		public void setCom(boolean com) {
			this.com = com;
		}
		public String getEx() {
			return ex;
		}
		public void setEx(String ex) {
			this.ex = ex;
		}
		public String getTns() {
			return tns;
		}
		public void setTns(String tns) {
			this.tns = tns;
		}
		public String getClCausedEx() {
			return clCausedEx;
		}
		public void setClCausedEx(String clCausedEx) {
			this.clCausedEx = clCausedEx;
		}
		public int getStCnt() {
			return stCnt;
		}
		public void setStCnt(int stCnt) {
			this.stCnt = stCnt;
		}
		public Integer getIndEx() {
			return indEx;
		}
		public void setIndEx(Integer indEx) {
			this.indEx = indEx;
		}
	}
	
	private static  class  DBTransactionStatementInfo implements Serializable{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 5761312972297075896L;
		String cl;
		String sql;
		long sT;
		long eT;
		long d;
		
		public DBTransactionStatementInfo() {
			// TODO Auto-generated constructor stub
		}
		public String getCl() {
			return cl;
		}
		public void setCl(String cl) {
			this.cl = cl;
		}
		public String getSql() {
			return sql;
		}
		public void setSql(String sql) {
			this.sql = sql;
		}
		public long getsT() {
			return sT;
		}
		public void setsT(long sT) {
			this.sT = sT;
		}
		public long geteT() {
			return eT;
		}
		public void seteT(long eT) {
			this.eT = eT;
		}
		public long getD() {
			return d;
		}
		public void setD(long d) {
			this.d = d;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
	};
}
