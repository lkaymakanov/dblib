package net.is_bg.ltf.db.common.impl.visit;

import net.is_bg.ltf.db.common.interfaces.visit.IVisit;

// TODO: Auto-generated Javadoc
/**
 * The Class VisitEmpty.
 */
public class VisitEmpty implements IVisit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2370537698002585561L;
	private VisitAdditionals visitAdditionals = new VisitAdditionals();

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.interfaces.visit.IVisit#getDbType()
	 */
	public DB_TYPE getDbType() {
		// TODO Auto-generated method stub
		return DB_TYPE.PGR;
	}

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.interfaces.visit.IVisit#getTransactionNo()
	 */
	public long getTransactionNo() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.interfaces.visit.IVisit#getFullName()
	 */
	public String getFullName() {
		// TODO Auto-generated method stub
		return "No name";
	}

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.interfaces.visit.IVisit#getTns()
	 */
	public String getTns() {
		// TODO Auto-generated method stub
		return "No Tns";
	}

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.interfaces.visit.IVisit#setTransactionNo(long)
	 */
	public void setTransactionNo(long no) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getCommittedTransactionCnt() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCommittedTransactionCnt(long committedTransactionCnt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getRollBackedTransactionCnt() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setRollBackedTransactionCnt(long rollBackedTransactionCnt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getVisitId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public VisitAdditionals getVisitAdditionals() {
		// TODO Auto-generated method stub
		return visitAdditionals;
	}
	

}
