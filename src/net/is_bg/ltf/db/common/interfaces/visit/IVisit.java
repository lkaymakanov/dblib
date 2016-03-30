package net.is_bg.ltf.db.common.interfaces.visit;




// TODO: Auto-generated Javadoc
/**
 * The Interface IVisit.
 */
public interface IVisit {

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
	
}
