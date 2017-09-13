package net.is_bg.ltf.db.common;

/***
 * A class that contains mark up constant for beginning/end of transactions & sql statements in transaction (transaction & statement details)!!!
 * @author lubo
 *
 */
public class DBTransactionMarkConstants {
	
	    /***euro sign*/
		public final static String  MAGICST  = new String(new char [] {'\u20AC'});    
		
		/**mark up beginning of transaction**/
		public final static String TRANSACTION_BEGIN =  "@@@_TR_B_@_";   
		
		final static String MAGICST_TRANS_BEG_STARTTIME =  "\n" + MAGICST + "%s\n" + TRANSACTION_BEGIN;
		
		/** mark up end of transaction**/
		public final static String TRANSACTION_END =  "_@_TR_E_@_"; // "\n_@_TR_E_@_\n%s\n";  // mark up end of transaction
		
		/**mark up end of transaction + end time**/
		final static String TRANSACTION_END_ENDTIME =  "\n" + TRANSACTION_END + "\n%s\n";        //mark up end of transaction + end time
		
		/** mark the beginning of statement**/
		public final static String STATEMENT_BEGIN =  "@STB@";   //"\n@STB@\n";    //mark the beginning of statement
		
		/***mark end of statement*/
		public final static String STATEMENT_END =  "@STE@";      //"\n@STE@";      //mark end of statement
		
		/**user name  mark up**/
		public final static String USER_NAME= "UNAME:";   //user name  mark up
		
		/***user id mark up*/
		public final static String USER_ID = "UID:";      //user id mark up
		
		/**user transaction number mark up**/
 		public final static String USER_TRNO = "UTRNo:";    //user transaction number mark up
 		
 		/**duration of transaction in millisecond**/
		public final static String DURATION = "D:";      //duration of transaction in milliseconds
		
		/**data base url mark up**/
		public final static String DB_URL = "DB_URL:";    //data base url mark up
		
		/**mark up the beginning of enumaration of  classes in db transaction  **/
		public final static String CLASSES = "$*====== CLASSES ======";          //mark up the beginning of enumaration of  classes in db transaction  
		
		/**mark up the beginning of SQL statements**/
 		public final static String SQLS = "&@#===== SQLS ======";               //mark up the beginning of SQL statements
 		
 		/**mark up the start time of sql statement **/
 		public final static String START_TIME= "ST:";                        //mark up the start time of sql statement 
 		
 		/**mark up the end time of sql statement**/
 		public final static String END_TIME = "ET:";                         //mark up the end time of sql statement

 		/**mark up of transaction id*/
		public static final String TRANSACTION_ID = "TRID:";                            //mark up of transaction id

		/*** mark up a begin of transaction exception*/
		public static final String EXCEPTION_BEGIN = "!*@_EX_BEG_@:";
		
		/*** mark up a end end transaction exception*/
		public static final String EXCEPTION_END = "!*@_EX_END_@:";
		
		/*** mark up a begin of stacktarace*/
		public static final String STACK_BEGIN = "@_STACK_BEG_@:";
		
		/*** mark up a end end stacktarace*/
		public static final String STACK_END = "@_STACK_END_@:";

		/**mark up that shows the number of statements in the transaction*/
		public static final String STATEMENT_COUNT = "STC:";
}
