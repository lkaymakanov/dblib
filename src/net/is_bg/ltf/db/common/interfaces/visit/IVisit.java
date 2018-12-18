package net.is_bg.ltf.db.common.interfaces.visit;

import java.io.Serializable;

import net.is_bg.ltf.db.common.impl.visit.VisitAdditionals;






// TODO: Auto-generated Javadoc
/**
 * The Interface IVisit.
 */
public interface IVisit extends Serializable{

	/**
	 * The Enum DB_TYPE.
	 */
	public enum DB_TYPE {/** The orcl. */
ORCL, /** The pgr. */
 PGR};
	
	/**
	 * Gets the db type.
	 *
	 * @return the db type
	 */
	public DB_TYPE getDbType();
	
	/**
	 * Gets the transaction no.
	 *
	 * @return the transaction no
	 */
	public long getTransactionNo();
	
	/**
	 * Gets the full name.
	 *
	 * @return the full name
	 */
	public String getFullName();
	
	/**
	 * Gets the tns.
	 *
	 * @return the tns
	 */
	public String getTns();
	
	/**
	 * Sets the transaction no.
	 *
	 * @param no the new transaction no
	 */
	public void setTransactionNo(long no);
	
	
	public long getCommittedTransactionCnt();
	public void setCommittedTransactionCnt(long committedTransactionCnt);
	public long getRollBackedTransactionCnt();
	public void setRollBackedTransactionCnt(long rollBackedTransactionCnt);
	public long getVisitId();
	public VisitAdditionals getVisitAdditionals();
}
