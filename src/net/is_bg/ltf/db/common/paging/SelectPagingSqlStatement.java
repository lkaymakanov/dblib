package net.is_bg.ltf.db.common.paging;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.is_bg.ltf.db.common.SelectSqlStatement;
import net.is_bg.ltf.db.common.interfaces.IAbstractModel;

public class SelectPagingSqlStatement<T extends IAbstractModel> extends SelectSqlStatement {
	protected List<T> result = new ArrayList<T>();
	protected int count;
	protected Integer comboIndex;
	protected long srcID;
	private int rowBegin =-1;
	private int rowEnd =-1;
	private boolean preCount = false;
	
	protected String rtnSqlString(String sql) {
		if (rowEnd <= 0) return sql;
		if (rowBegin > rowEnd) return sql;
		String resultStr = "select * from (select a.*, ROW_NUMBER() OVER (ORDER BY 0) rnum from ( " + sql + " ) a ) b where rnum  >= "+rowBegin+" and rnum <= "+rowEnd+" ";
        return resultStr;
    }
	
	protected String rtnSqlCount(String sql) {
        return "select count(*) " + sql.split("order")[0];
    }

    protected void setParameters(PreparedStatement prStmt) throws SQLException {
        bindVarData.setParameters(prStmt);
    }

    
    public void setRows(int rowBegin, int rowEnd) {
		this.rowBegin = rowBegin;
		this.rowEnd = rowEnd;
	}
    
	@Override
    protected String getSqlString() {
	    return null;
    }
	
	public List<T> getResult() {
		return result;
	}
	
	public int getCount() {
		return count;
	}

	public boolean isPreCount() {
		return preCount;
	}

	public void setPreCount(boolean preCount) {
		this.preCount = preCount;
	}
}