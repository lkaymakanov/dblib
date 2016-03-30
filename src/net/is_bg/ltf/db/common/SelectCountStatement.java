package net.is_bg.ltf.db.common;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


// TODO: Auto-generated Javadoc
/**
 * The Class SelectCountStatement.
 */
public class SelectCountStatement extends SelectSqlStatement{
	
	/** The wrapped stat. */
	SelectSqlStatement wrappedStat = null;
	
	/** The before. */
	String BEFORE = "select count(*) from ( ";
	
	/** The after. */
	String AFTER = " ) rtbl";	
	
	/** The count. */
	int count;
	
	/**
	 * Instantiates a new select count statement.
	 *
	 * @param wrappedStat the wrapped stat
	 */
	public SelectCountStatement(SelectSqlStatement wrappedStat){
		this.wrappedStat = wrappedStat;
	}

	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.SqlStatement#getSqlString()
	 */
	@Override
	protected String getSqlString() {
		// TODO Auto-generated method stub
		//delegate to wrapped statement
		String sql = wrappedStat.getSqlString();
		return BEFORE + sql + AFTER;
	}
	
	
	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.SqlStatement#setParameters(java.sql.PreparedStatement)
	 */
	@Override
	protected void setParameters(PreparedStatement prStmt) throws SQLException {
		// TODO Auto-generated method stub
		wrappedStat.setParameters(prStmt);
	}
	
	
	//getCount
	/* (non-Javadoc)
	 * @see net.is_bg.ltf.db.common.SelectSqlStatement#retrieveResult(java.sql.ResultSet)
	 */
	@Override
	protected void retrieveResult(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		while (rs.next()) {
			count =  rs.getInt(1);
		}
	}
	
	
	/**
	 * Gets the row count.
	 *
	 * @return the row count
	 */
	public int getRowCount(){
		return count;
	}

}
