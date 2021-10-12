package net.is_bg.ltf.db.common;

import java.sql.ResultSet;
import java.util.Map;

import net.is_bg.ltf.db.common.customsql.IResultSetData;

interface IStoreResultStrategy {
	IResultSetData getResult(ResultSet rs, Map storage,  String sqlforLog);
	int getResultSetType(); 
	int getResultSetConcurrency();
}
