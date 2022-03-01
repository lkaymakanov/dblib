package net.is_bg.ltf.db.common.client;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.List;
import java.util.function.Function;

import javax.sql.DataSource;

import net.is_bg.ltf.db.common.SelectSqlStatement;
import net.is_bg.ltf.db.common.StoredProcedure;
import net.is_bg.ltf.db.common.UpdateSqlStatement;
import net.is_bg.ltf.db.common.customsql.ColumnMetaData;
import net.is_bg.ltf.db.common.customsql.CustomSqlUtils;
import net.is_bg.ltf.db.common.customsql.IResultSetData;

public class CustomClientUtils {
	public static Function<Object, String> convertResultSet;
	public static Function<Object, String> convertPrepStatementAdditionalData;
	public static Function<IResultSetData, ResultSet> resultsetdataToResutSet = new Function<IResultSetData, ResultSet>() {
		@Override
		public ResultSet apply(IResultSetData t) {
			return new CustomResultSet(t);
		}
	};
	
	public static DataSource createCustomDs(ICustomDSProperties prop) {
		return new CustomDataSource(prop);
	}
	
	static ResultSet toResultSet(IResultSetData resultSetData) {
	    return	resultsetdataToResutSet.apply(resultSetData);
	}
	
	
	static String toString(List<ICustomParam> paramList) {
		CustomParamsObject po = new CustomParamsObject();
		po.params = paramList;
		return convertResultSet.apply(po);
	}
	
	static String toString(PrepStatementAdditionalData paramList) {
		return convertPrepStatementAdditionalData.apply(paramList);
	}
	
	static ResultSetMetaData toMetaData(List<ColumnMetaData> metaData) {
		throw new UnsupportedOperationException();
	}
	
	public static SelectSqlStatement createSelectStmt(IParamsSqlAdditionalData pdata) {
		return  CustomSqlUtils.createCustomSelectStmt(pdata, true);
	}
	
	public static StoredProcedure createStProc(IParamsSqlAdditionalData pdata) {
		return CustomSqlUtils.createStoredProc(pdata);
	}
	public static IResultSetData executeStProc(StoredProcedure s, Connection c) {
		return  CustomSqlUtils.executeStoredProc(s,c);
	}
	
	
	public static IResultSetData executeSelectStmt(SelectSqlStatement s, Connection c) {
		return  CustomSqlUtils.executeCustomSelect(s,c);
	}
	
	public static UpdateSqlStatement createUpdateStmt(IParamsSqlAdditionalData pdata) {
		return CustomSqlUtils.createUpdateStmt(pdata);
	}
	
	public static IResultSetData executeUpdateStmt(UpdateSqlStatement upd, Connection c) {
		return CustomSqlUtils.executeCustomUpdateStmt(upd, c);
	}

}