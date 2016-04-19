package net.is_bg.ltf.db.common.customsql;

import java.util.List;

public interface IResultSetData  {
	List<ColumnMetaData> getColumMetaData();
	List<Object[]>	getResult();
	Exception getException();
}
