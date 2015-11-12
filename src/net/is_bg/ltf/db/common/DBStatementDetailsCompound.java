package net.is_bg.ltf.db.common;

import net.is_bg.ltf.db.common.interfaces.logging.ILog;



public class DBStatementDetailsCompound extends DBStatementDetails{
	
	private DBStatementDetails [] details;
	
	public DBStatementDetailsCompound(DBStatementDetails [] details){
		this.details = details;
	}
	
	@Override
	public String printDetails(ILog LOG) {
		// TODO Auto-generated method stub
		for(int i = 0; i < details.length; i++){
			details[i].printDetails(LOG);
		}
		return "";
	}
	

}
