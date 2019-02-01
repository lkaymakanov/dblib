package net.is_bg.ltf.db.common.customsql;


import java.util.List;

public interface IResultSetDataManager {
	public int getColsCnt();
	public int getRowsCnt();
	public ColumnMetaData getColumnMetaData(int  colIndex);
	public IRowData getRowData(int rowIndex);
	public List<ColumnMetaData> getColumnsMeataData();
	public List<Object[]> getRows();
}

