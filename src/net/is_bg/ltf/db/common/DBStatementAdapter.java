package net.is_bg.ltf.db.common;
import java.sql.ResultSetMetaData;

import net.is_bg.ltf.db.common.customsql.IResultSetData;
import net.is_bg.ltf.db.common.interfaces.IResultSetMetaDataListener;
import net.is_bg.ltf.db.common.interfaces.visit.IVisit;




// TODO: Auto-generated Javadoc
/**
 * The Class DBStatementAdapter.
 */
public abstract  class  DBStatementAdapter  implements DBStatement{

	/** The bind var data. */
	protected BindVariableData bindVarData = new BindVariableData();
	
	/** The details. */
	DBStatementDetails details = new DBStatementDetails();
	
	/** The test. */
	public static  boolean test = false;
	
	/** The name of procedure or the query string. */
	protected String sql = "";
	
	/** The collect usr details. */
	private boolean collectUsrDetails = true;
	
	protected IResultSetMetaDataListener resultSetMetaDataListener;
	protected IStoreResultStrategy storeResultsetStrategy = null;
	protected static volatile IResultSetStorageProvider stprovider = null;
	protected IResultSetData stored = null;
	
	public DBStatementAdapter() {
		storeResultsetStrategy =  stprovider == null ? null : new CommonStoreStrategy(this, stprovider);
	}
	
	/**
	 * Displays resulset metadata.
	 *
	 * @param metadata the metadata
	 */
	protected void displayMetaData(ResultSetMetaData metadata){
		try
		{
			if(metadata == null ) return;
			ResultSetMetaData meta =  metadata;
			int cols = meta.getColumnCount();
			int cur = 0;
			System.out.println("Printing column MetaData ==================================================");
			for(int i = 0; i < cols; i++){
				cur = i+1;
				System.out.println();
				System.out.println("COLUMN NUMBER = " + cur);
				System.out.println("column class name = " + meta.getColumnClassName(cur));
				System.out.println("column display size =  " +  meta.getColumnDisplaySize(cur));
				System.out.println("column label = " +  meta.getColumnLabel(cur));
				System.out.println("column name = " +  meta.getColumnName(cur));
				System.out.println("column type = " +  meta.getColumnType(cur));
				System.out.println("column type name  = " +  meta.getColumnTypeName(cur));
				System.out.println("presicion =  " +  meta.getPrecision(cur));
				System.out.println("scale =  " +  meta.getScale(cur));
				System.out.println("schema name = " +  meta.getSchemaName(cur));
				System.out.println("table name = " +  meta.getTableName(cur));
			}
			System.out.println("End printing column MetaData ==================================================");
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * Gather user info.
	 *
	 */
	public void collectUserDetails()  {
		if(collectUsrDetails){
			IVisit visit = DBConfig.getVisitFactory().getVist();
			if(visit != null){
				
				//set tranasction no
				details.setTransactionNo(visit.getTransactionNo());
				
				//tns
				details.setTns(visit.getTns());
				
				//user name
			    details.setUserName(visit.getFullName());
			    
			    //set statement class
			    details.setStatemetntClass(this.getClass());
			}
		}
		details.setSlqForlog(sqlForLog());
	}
	
	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.DBStatement#getDetails()
	 */
	public DBStatementDetails getDetails() {
		// TODO Auto-generated method stub
		return details;
	}
	
	
	/**
	 * Sql for log.
	 *
	 * @return the string
	 */
	public String sqlForLog() {
		return bindVarData.sqlForLog(sql);
	}

	/**
	 * Checks if is test.
	 *
	 * @return true, if is test
	 */
	public static boolean isTest() {
		return test;
	}

	/**
	 * Sets the test.
	 *
	 * @param test the new test
	 */
	public static void setTest(boolean test) {
		DBStatementAdapter.test = test;
	}

	/**
	 * Sets the collect usr details.
	 *
	 * @param collectUsrDetails the new collect usr details
	 */
	public void setCollectUsrDetails(boolean collectUsrDetails) {
		this.collectUsrDetails = collectUsrDetails;
	}	
	
	public IResultSetData getStored() {
		return stored;
	}
	
	public static void setStprovider(IResultSetStorageProvider stprovider) {
		DBStatementAdapter.stprovider = stprovider;
	}
	
	protected boolean isValidStr() {
		return (storeResultsetStrategy !=null && stprovider!=null && stprovider.getStorage()!=null);
	}
	
	public void setStored(IResultSetData stored) {
		this.stored = stored;
	}
	
	/*
	 * 
	 * 
*/
	
}
