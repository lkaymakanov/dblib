package net.is_bg.ltf.db.common;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SelectCountStatement extends SelectSqlStatement{
	
	SelectSqlStatement wrappedStat = null;
	String BEFORE = "select count(*) from ( ";
	String AFTER = " ) rtbl";	
	int count;
	
	public SelectCountStatement(SelectSqlStatement wrappedStat){
		this.wrappedStat = wrappedStat;
	}

	@Override
	protected String getSqlString() {
		// TODO Auto-generated method stub
		//delegate to wrapped statement
		String sql = wrappedStat.getSqlString();
		return BEFORE + sql + AFTER;
	}
	
	
	@Override
	protected void setParameters(PreparedStatement prStmt) throws SQLException {
		// TODO Auto-generated method stub
		wrappedStat.setParameters(prStmt);
	}
	
	
	//getCount
	@Override
	protected void retrieveResult(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		while (rs.next()) {
			count =  rs.getInt(1);
		}
	}
	
	
	public int getRowCount(){
		return count;
	}

}
