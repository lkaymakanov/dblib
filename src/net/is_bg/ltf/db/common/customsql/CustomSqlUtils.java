package net.is_bg.ltf.db.common.customsql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import net.is_bg.ltf.db.common.DBConfig;
import net.is_bg.ltf.db.common.DBExecutor;
import net.is_bg.ltf.db.common.SelectSqlStatement;
import net.is_bg.ltf.db.common.StoredProcedure;
import net.is_bg.ltf.db.common.UpdateSqlStatement;
import net.is_bg.ltf.db.common.client.ICustomParam;
import net.is_bg.ltf.db.common.client.IParamsSqlAdditionalData;
import net.is_bg.ltf.db.common.interfaces.IConnectionFactory;

public class CustomSqlUtils {
	
	
	private static class UpdateSqlStatementEx  extends UpdateSqlStatement {
		private String sql;
		private IParamsSqlAdditionalData params;
		@Override
		protected String getSqlString() {
			return sql;
		}

		@Override
		public boolean isUpdate() {
			return true;
		}

		@Override
		public boolean isStoredProcedure() {
			return false;
		}
		
		@Override
		protected void setParameters(PreparedStatement prStmt) throws SQLException {
			if(params != null) {
				for(ICustomParam p : params.getParams()){
					prStmt.setObject(p.getPosition(), p.getValue(), p.getSqlType());
				}
			}
		}
	};
	
	private static class StoredProc extends StoredProcedure{
		private IParamsSqlAdditionalData params;
		private List<Integer> outParamInd = new ArrayList<Integer>();
		private int maxOutParamIndex  = 0;
		ResultSetData rs = new ResultSetData();
		
		@Override
		protected String getProcedureName() {
			return params.getAdditionalData().getSql();
		}

		@Override
		protected void setParameters(CallableStatement callableStatement) throws SQLException {
			for(ICustomParam p:params.getParams()) {
				int paramType =  p.getParamTypeInt();
				if(paramType == 3) {
					if(p.getPosition() > maxOutParamIndex) maxOutParamIndex = p.getPosition();
					outParamInd.add(p.getPosition());
					callableStatement.setObject(p.getPosition(), p.getValue(),p.getSqlType());
					callableStatement.registerOutParameter(p.getPosition(), p.getSqlType());
				}
				if(paramType == 1) {
					callableStatement.setObject(p.getPosition(), p.getValue(), p.getSqlType());
				} else if(paramType == 2) {
					if(p.getPosition() > maxOutParamIndex) maxOutParamIndex = p.getPosition();
					outParamInd.add(p.getPosition());
					callableStatement.registerOutParameter(p.getPosition(), p.getSqlType());
				}
			}
		}
		@Override
		protected void retrieveResult(CallableStatement callableStatement) throws SQLException {
			Object [] row = new Object[maxOutParamIndex+1];
			for(int i = 1; i <=maxOutParamIndex; i++){
				Object o = null;
				if(outParamInd.contains(i)) {
					o = callableStatement.getObject(i);
				}
				row[i-1] = (o ==null ? null: net.is_bg.ltf.db.common.client.SqlTypeConverter.toString(o));
			}
			rs.getResult().add(row);
		}
	}
	
	public static StoredProcedure createStoredProc(IParamsSqlAdditionalData pdata) {
		StoredProc sp = new StoredProc();
		sp.params = pdata;
		return sp;
	}
	
	
	public static IResultSetData executeStoredProc(StoredProcedure proc, Connection c) {
		if(proc instanceof StoredProc) {
			StoredProc st =  (StoredProc)proc;
			st.execute(c);
			return st.rs;
		}
		//sp.sql = sql;
		return null;
	}
	
	public static UpdateSqlStatement createUpdateStmt(IParamsSqlAdditionalData pdata) {
		final String sql = pdata.getAdditionalData().getSql();
		UpdateSqlStatementEx upd = new UpdateSqlStatementEx();
		upd.sql = sql;
		upd.params = pdata;
		return upd;
	}
	
	public static IResultSetData executeCustomUpdateStmt(UpdateSqlStatement upd, Connection c) {
		if(upd instanceof UpdateSqlStatementEx) {
			UpdateSqlStatementEx u = (UpdateSqlStatementEx)upd;
			u.execute(c);
			return getUpdateResult(u);
		}
		return null;
	}
	
	private static IResultSetData getUpdateResult(UpdateSqlStatement u){
		ResultSetData data = new ResultSetData();
		ColumnMetaData meta = new ColumnMetaData();
		meta.setColumnName("count");
		meta.setCatalogName("");
		meta.setColumnClassName("");
		meta.setColumnType(Types.BIGINT);
		meta.setDisplaySize(100);
		meta.setScale(0);
		meta.setSchemaName("");
		meta.setTableName("count");
		data.getColumnMetaData().add(meta);
		Object [] d = new Object [1];
		d[0] = u.getUpdateCnt();
		data.getResult().add(d);
		return data;
	}

	
	@SuppressWarnings("rawtypes")
	public static SelectSqlStatement createCustomSelectStmt(IParamsSqlAdditionalData pdata, boolean convertDateTolong) {
		 return  new CustomSelect(pdata.getAdditionalData().getSql(), pdata, convertDateTolong);
	}
	
	public static IResultSetData executeCustomSelect(SelectSqlStatement customSelect, Connection c) {
		if(customSelect instanceof CustomSelect) {
			CustomSelect<?> cs = (CustomSelect<?>)customSelect;
			cs.execute(c);
			return cs.getResultSetData();
		}
		return null;
	}

	
	private static CustomSelect toSelect(String sql) {
		return new CustomSelect<>(sql);
	}
	
	private static UpdateSqlStatementEx toUpdate(String sql) {
		UpdateSqlStatementEx upd = new UpdateSqlStatementEx();
		upd.sql = sql;
		return upd;
	}

	
	private static class DbExec extends DBExecutor{
		public DbExec(IConnectionFactory factory, boolean stlt) {
			super(factory);
			this.stealth = stlt;
		}
	}
	
	
	public static IResultSetData executeCustomSelect(CustomSqlConfigParams cp) {
		CustomSelect s = toSelect(cp.getSql());
		s.setRows(cp.getRowBegin(), cp.getRowEnd());
		new DbExec(DBConfig.getConnectionFactory(), cp.isStealth()).execute(s,cp.getDataSource(), Connection.TRANSACTION_READ_COMMITTED);
		return s.getResultSetData();
	}


	public static IResultSetData execSql(CustomSqlConfigParams cp) {
		String sql = cp.getSql();
		return sql.trim().toLowerCase().startsWith("select") ? executeCustomSelect(cp):exeUpd(cp);
	}


	public static IResultSetData exeUpd(CustomSqlConfigParams cp) {
		UpdateSqlStatementEx s = toUpdate(cp.getSql());
		new DbExec(DBConfig.getConnectionFactory(), cp.isStealth()).execute(s, cp.getDataSource(), Connection.TRANSACTION_READ_COMMITTED);
	    return getUpdateResult(s);
	}
}
