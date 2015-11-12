package net.is_bg.ltf.db.common.impl.visit;

import net.is_bg.ltf.db.common.interfaces.visit.IVisit;

public class VisitEmpty implements IVisit {

	public DB_TYPE getDbType() {
		// TODO Auto-generated method stub
		return DB_TYPE.PGR;
	}

	public long getTransactionNo() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getFullName() {
		// TODO Auto-generated method stub
		return "No name";
	}

	public String getTns() {
		// TODO Auto-generated method stub
		return "No Tns";
	}

	public void setTransactionNo(long no) {
		// TODO Auto-generated method stub
		
	}

}
