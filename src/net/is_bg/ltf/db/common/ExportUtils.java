package net.is_bg.ltf.db.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExportUtils {
	public static String exportStorageHtml(Map storage) {
		if(storage == null) return null;
		List<ResultSetDataSql> l = new ArrayList<ResultSetDataSql>();
		Object st = storage.get("raw");
		if(!(st instanceof Map)) return null;
		storage = (Map)st;
		for(Object v: storage.values()) {
			if(!(v instanceof ResultSetDataSql)) return null;
			l.add((ResultSetDataSql)v);
		}
		return ResultSetDataFormatter.asHtmlSql(l);
	}
}
