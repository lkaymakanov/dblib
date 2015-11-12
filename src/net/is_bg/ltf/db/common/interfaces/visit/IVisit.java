package net.is_bg.ltf.db.common.interfaces.visit;




public interface IVisit {

	public enum DB_TYPE {ORCL, PGR};
	public DB_TYPE getDbType();
	
	public long getTransactionNo();
	public String getFullName();
	public String getTns();
	public void setTransactionNo(long no);
	
}
