package net.is_bg.ltf.db.common;

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
 * A class that represents an sql transaction!
 * @author lubo
 *
 */
public class DBTransaction implements IDBTransaction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2110666118807504254L;
	private DBStatement [] statements;
	private Connection connection;
	private ILog  LOG;
	private int i = 0;
	private ITransactionListener listener;
	//a global transaction id!
	private static AtomicLong transactionIdSequence = new AtomicLong(0);
	private boolean rollBacked = false;
	private IVisit visit;
	private long transactionId;
	private IElaplsedTimer timer = DBConfig.getTimerFactory().getElapsedTimer();
	static AtomicLong commitedTransactions = new AtomicLong(0);
	static AtomicLong rollbackedTranasactions = new AtomicLong(0);
	private List<DBStatementDetails> dbStatementDetails = new ArrayList<DBStatementDetails>();
	private String tns;
	private String userName;
	
	private DBTransaction(DBStatement [] statements, Connection connection, ILog LOG, ITransactionListener transActionListener){
		this.statements = statements;
		this.connection = connection;
		this.transactionId = transactionIdSequence.incrementAndGet();
		this.listener = transActionListener;
		this.LOG = LOG;
	}
	
	
	
	public void execute() {
		visit = DBConfig.getVisitFactory().getVist();
		//user local transaction id
		if(visit != null){
			visit.setTransactionNo(visit.getTransactionNo() + 1);
			visit.getTransactionNo();
		}
		try{
			timer.start();
			for (; i < statements.length; i++) {
				statements[i].getDetails().setTransactionIsolationLevel(connection.getTransactionIsolation());
				statements[i].execute(connection);
			}
			timer.stop();
			LOG.debug(getTransactionClassesAndSqls());
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new JDBCException(e);
		}
	}
	
	public void commit(){
		try {
			connection.commit();
			commitedTransactions.incrementAndGet();
			if(listener!=null)listener.afterCommit(this);
			//addCommitedTransaction();
			if(visit != null) visit.setCommittedTransactionCnt(visit.getCommittedTransactionCnt() + 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new JDBCException(e);
		}
	}
	

	public void rollBack(){
		try {
			if(visit != null) visit.setRollBackedTransactionCnt(visit.getRollBackedTransactionCnt() + 1);
			connection.rollback();
			rollbackedTranasactions.incrementAndGet();
			if(listener!=null)listener.afterRollBack(this);
			//addRollbackedTransaction();
			//ApplicationGlobals.getApplicationGlobals().addRollBackedTransaction(this);
			rollBacked = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new JDBCException(e);
		}
	}
	

	public void setTransactionIsolation(int isolationLevel){
		try {
			//connection.setTransactionIsolation(isolationLevel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new JDBCException(e);
		}
	}
	
	public void setAutoCommit(boolean autoCommit){
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new JDBCException(e);
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


	
	private String getTransactionClassesAndSqls (){
		if(statements == null || statements.length == 0) return "";
		StringBuilder sbclasses = new StringBuilder();
		StringBuilder sSqls = new StringBuilder();
		DBStatementDetails ds = statements[0].getDetails();
		tns = ds.getTns();
		userName = ds.getUserName();
		sbclasses.append("\n============================== BEGIN TRANSACTION " + transactionId + " ==============================");
		sbclasses.append("\nApp User:    " + userName);
		sbclasses.append("\nUser TransAction No :" + (visit == null ? 0 : visit.getTransactionNo()));
		sbclasses.append("\nTransAction Start time:" + new Date(timer.getStartTime()));
		sbclasses.append("\nTransAction End Time:" + new Date(timer.getEndTime()));
		sbclasses.append("\nDuration:" + timer.getDuration() + " ms.");
		sbclasses.append("\nDB URL:      " + tns);
		sbclasses.append("\n============================== CLASSES in TRANSACTION ==============================\n");
		sSqls.append("============================== SQLS in TRANSACTION  ==============================\n");
		for(DBStatement db : statements){
			DBStatementDetails d = db.getDetails();
			dbStatementDetails.add(d);
			sbclasses.append(db.getClass());
			sbclasses.append("\n");
			sSqls.append(d.getSlqForlog());
			sSqls.append("\nStart time:" + new Date(d.getStartTime()));
			sSqls.append("\nEnd Time:" + new Date(d.getEndTime()));
			sSqls.append("\nDuration:" + d.getDuration() + " ms\n");
			sSqls.append("\n");
		}
		sSqls.append("============================== END TRANSACTION " + transactionId + " ==============================\n");
		return sbclasses.append(sSqls.toString()).toString();
	}
	


	/**Check if transaction has update statement*/
	public boolean isLogUpdate(){
		if(visit == null || !visit.getVisitAdditionals().isLogUpdate()) return false;
		if(statements.length > 1) return true;
		for(DBStatement db : statements){
			if(db instanceof UpdateSqlStatement || db instanceof StoredProcedure) return true;
		}
		return false;
	}
	
	/**Check if Select is loggable*/
	public boolean isLogSelect() {
		// TODO Auto-generated method stub
		return  (visit != null && visit.getVisitAdditionals().isLogSelect() &&  ((statements.length == 1 || statements[0] instanceof SelectSqlStatement)));
	}
	
	public  long getStartTime(){
		return timer.getStartTime();
	}
	
	public long getEndTime(){
		return timer.getEndTime();
	}
	
	public long getDuration(){
		return timer.getDuration();
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
		
		public DBTransactionBuilder (DBStatement [] statements, Connection connection, ILog LOG){
			this.statements = statements;
			this.connection = connection;
			this.LOG = LOG;
		}
		
		
		public IDBTransaction build(){
			return new DBTransaction(statements, connection, LOG, transActionListener);
		}
	}
	
}
