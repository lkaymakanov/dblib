package net.is_bg.ltf.db.common.client;

import javax.sql.DataSource;

public class DataSourceUtils {

	public static DataSource wrapDataSource(DataSource ds) {
		return new DatasourceWrapper(ds);
	}
	
	public static DataSource wrapDataSource(DataSource ds, IConnectionRegister reg) {
		return new DatasourceWrapper(ds, reg);
	}
	
	public static IConnectionRegister createConnectionRegister() {
		return new ConnReg();
	}
	
	public static DataSource createCustomDataSource(ICustomDSProperties prop) {
		return new CustomDataSource(prop);
	}
}
