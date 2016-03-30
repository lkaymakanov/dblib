package net.is_bg.ltf.db.common.interfaces;

import java.sql.Connection;

/**An extended IconnectionFactory interface that provides us with method to get Connection by its resource name **/
public interface IConnectionFactoryX extends IConnectionFactory {
	
	/***
	 * Get Connection by its resource name in server.xml
	 * @param name
	 * @return
	 */
	public Connection getConnection(String name);
	
}
