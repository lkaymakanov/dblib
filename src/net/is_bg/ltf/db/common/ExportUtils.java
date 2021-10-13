package net.is_bg.ltf.db.common;

import java.util.List;
import java.util.Map;

import net.is_bg.ltf.db.common.customsql.IResultSetData;

public class ExportUtils {
	public static String exportStorageHtml(Map storage, long f) {
		if(storage == null) return null;
		Object st = storage.get("raw");
		if(!(st instanceof List)) return null;
	    List<ResultSetDataSql>	sto = (List<ResultSetDataSql>)st;
		return ResultSetDataFormatter.asHtmlSql(sto, f);
	}
	
	public static String exportStorage(SelectSqlStatement select, long f) {
		IResultSetData data=  select.getStored();
		if(data == null) return "";
		if(!(data instanceof ResultSetDataSql)) return "";
		return ResultSetDataFormatter.asHtmlTable((ResultSetDataSql)data, f);
	}
	
	public static String wrapAsBody(String t) {
		return ResultSetDataFormatter.wrapAsBody(t);
	}
}
