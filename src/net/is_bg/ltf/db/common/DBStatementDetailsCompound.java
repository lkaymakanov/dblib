package net.is_bg.ltf.db.common;

import net.is_bg.ltf.db.common.interfaces.logging.ILog;



// TODO: Auto-generated Javadoc
/**
 * The Class DBStatementDetailsCompound.
 */
public class DBStatementDetailsCompound extends DBStatementDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1142298606283271558L;
	/** The details. */
	private DBStatementDetails [] details;
	
	/**
	 * Instantiates a new dB statement details compound.
	 *
	 * @param details the details
	 */
	public DBStatementDetailsCompound(DBStatementDetails [] details){
		this.details = details;
	}
	
	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.DBStatementDetails#printDetails(net.is_bg.ltf.db.common.interfaces.logging.ILog)
	 */
	@Override
	public String printDetails(ILog LOG) {
		// TODO Auto-generated method stub
		for(int i = 0; i < details.length; i++){
			details[i].printDetails(LOG);
		}
		return "";
	}
	

}
