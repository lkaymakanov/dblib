package net.is_bg.ltf.db.common.client;

import javax.sql.DataSource;

/**
 * Used to wrap data source in order to register the Connection coming from wrapped data source!!!
 * @author lkaymakanov
 *
 */
public class DataSourceUtils {
   
	
	public static DataSource wrapDataSource(DataSource ds) {
		return new DatasourceWrapper(ds);
	}
	/**
	 * Wraps a server datasource using a specified connection register....
	 * @param ds
	 * @param reg
	 * @return
	 */
	public static DataSource wrapDataSource(DataSource ds, IConnectionRegister reg) {
		return new DatasourceWrapper(ds, reg);
	}
	
	/***
	 * <pre>
	 * Creates a connection register!!!!
	 * Use on the server side to register connection and later retrieve, exec transactions and release it.
	 * <pre>
	 * @return
	 */
	public static IConnectionRegister createConnectionRegister() {
		return new ConnReg();
	}
	
	/***
	 * <pre>
	 * Used to create client datasource from custom datasource properties...
	 * Use on the client side when connection to remote ds...
	 * <pre>
	 * @param prop
	 * @return
	 */
	public static DataSource createCustomDataSource(ICustomDSProperties prop) {
		return new CustomDataSource(prop);
	}
}
