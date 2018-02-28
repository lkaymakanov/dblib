package net.is_bg.ltf.db.common.customsql;

import java.util.List;

public interface IResultSetData  {
	List<ColumnMetaData> getColumnMetaData();
	List<Object[]>	getResult();
	Exception getException();
}
