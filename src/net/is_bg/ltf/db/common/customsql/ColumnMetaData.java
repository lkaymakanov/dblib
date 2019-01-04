package net.is_bg.ltf.db.common.customsql;

public class ColumnMetaData {

	private String columnName;
	private int columnType;
	private String columnClassName;
	private String catalogName;
	private String tableName;
	private String columnLabel;
	private int precision;
	private int displaySize;
	private int scale;
	private String schemaName;
	
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public int getColumnType() {
		return columnType;
	}
	public void setColumnType(int columnType) {
		this.columnType = columnType;
	}
	public String getColumnClassName() {
		return columnClassName;
	}
	public void setColumnClassName(String columnClassName) {
		this.columnClassName = columnClassName;
	}
	public String getCatalogName() {
		return catalogName;
	}
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public int getPrecision() {
		return precision;
	}
	public void setPrecision(int precision) {
		this.precision = precision;
	}
	public int getDisplaySize() {
		return displaySize;
	}
	public void setDisplaySize(int displaySize) {
		this.displaySize = displaySize;
	}
	

	public void setScale(int scale) {
		this.scale = scale;
	}
	public int getScale() {
		return scale;
	}
	
	public void setColumnLabel(String columnLabel) {
		this.columnLabel= columnLabel;
	}
	
	public String getColumnLabel() {
		return columnLabel;
	}
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
	
	public String getSchemaName() {
		return schemaName;
	}
	
	@Override
	public String toString() {
		return catalogName + "." + tableName + "." + columnName +", " + columnType + ", " + precision;
	}
}
