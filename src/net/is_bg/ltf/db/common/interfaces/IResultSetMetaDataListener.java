package net.is_bg.ltf.db.common.interfaces;

import java.sql.ResultSetMetaData;

public interface IResultSetMetaDataListener {

	public void processMetaData(ResultSetMetaData metadata);
	
}
