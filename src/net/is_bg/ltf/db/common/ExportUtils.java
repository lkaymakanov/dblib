package net.is_bg.ltf.db.common;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import net.is_bg.ltf.db.common.customsql.IResultSetData;

public class ExportUtils {
	public static String exportStorageHtml(Map storage, long f) {
		if(storage == null) return null;
		Object st = storage.get("raw");
		if(!(st instanceof List)) return null;
	    List<ResultSetDataSql>	sto = (List<ResultSetDataSql>)st;
		return ResultSetDataFormatter.asHtmlSql(filter(sto, f), f);
	}
	
	
	public static String exportStorageHtml(Map storage) {
		if(storage == null) return null;
		Object st = storage.get("raw");
		if(!(st instanceof List)) return null;
	    List<ResultSetDataSql>	sto = (List<ResultSetDataSql>)st;
		return ResultSetDataFormatter.asHtmlSql(filter(sto, -1), -1);
	}
	
	public static String exportStorage(DBStatementAdapter dbSt, long f) {
		IResultSetData data=  dbSt.getStored();
		if(data == null) return "";
		if(!(data instanceof ResultSetDataSql)) return "";
		if(!filter((ResultSetDataSql)data, f)) return "";
		return ResultSetDataFormatter.asHtmlTable((ResultSetDataSql)data, f);
	}
	
	public static String wrapAsBody(String t) {
		return ResultSetDataFormatter.wrapAsBody(t);
	}
	
	private static List<ResultSetDataSql> filter(List<ResultSetDataSql> tobefiltered, long f){
		if(tobefiltered == null || tobefiltered.isEmpty()) return tobefiltered;
		return tobefiltered.stream().filter(new Predicate<ResultSetDataSql>() {
			@Override
			public boolean test(ResultSetDataSql t) {
				return ((t.type & f)!=0);
			}
		}).collect(Collectors.toList());
	}
	
	private static boolean filter(ResultSetDataSql tobefiltered, long f){
		if(tobefiltered == null ) return false;
		return true;
	}
	
}
